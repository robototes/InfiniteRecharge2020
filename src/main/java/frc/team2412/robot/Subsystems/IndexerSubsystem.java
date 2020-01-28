package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Commands.IndexerCommands.IntakeBalls;
import frc.team2412.robot.Commands.IndexerCommands.ProcessBalls;
import frc.team2412.robot.Commands.IndexerCommands.ShootBalls;
import frc.team2412.robot.Commands.IndexerCommands.SwitchBalls;

public class IndexerSubsystem extends SubsystemBase {
	public CANSparkMax indexBackMotor, indexFrontMotor, indexMidMotor;
	public SpeedControllerGroup indexMotors, indexSideMotors;

	public DigitalInput back, backMid, mid, frontMid, front, intakeFront, intakeBack;

	// NUMBER OF BALLS IN SYSTEM
	public int numBalls = 0;

	// SIDE OF INDEXER WITH LESS BALLS
	public IntakeDirection ballUnbalancedSide;

	// to run function once until reset
	private int run = 0;

	public IndexerSubsystem() {
		// Motors
		indexBackMotor = RobotMap.indexFrontMotor;
		indexFrontMotor = RobotMap.indexBackMotor;
		indexMidMotor = RobotMap.indexMidMotor;
		indexMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor, indexMidMotor);
		indexSideMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor);

		// Sensors
		back = RobotMap.back;
		backMid = RobotMap.backMid;
		mid = RobotMap.mid;
		frontMid = RobotMap.frontMid;
		front = RobotMap.front;

		intakeFront = RobotMap.intakeFront;
		intakeBack = RobotMap.intakeBack;

		setDefaultCommand(new ProcessBalls(this, new ShootBalls(this), new IntakeBalls(this), new SwitchBalls(this)));
	}

	// shifters

	// Motors
	public void stopAll() {
		indexMotors.set(0.0);
	}

	public void stopSides() {
		indexSideMotors.set(0.0);
	}

	// Sensors
	public boolean allSensorsOn() {
		if (back.get() && backMid.get() && mid.get() && frontMid.get() && front.get()) {
			return true;
		}

		return false;
		// check if all sensors are on
	}

	public boolean allSensorsOff() {
		if (!back.get() && !backMid.get() && !mid.get() && !frontMid.get() && !front.get()) {
			return true;
		}

		return false;
		// check if all sensors are on
	}

	public void shoot() {
		// allShiftDown();
		indexMidMotor.set(1);
		if (ballUnbalancedSide == IntakeDirection.FRONT) {
			indexBackMotor.set(-1);
		} else {
			indexFrontMotor.set(1);
		}
		if (frontMid.get() && front.get()) {
			indexFrontMotor.set(0);
			ballUnbalancedSide = IntakeDirection.FRONT;
		} else if (backMid.get() && back.get()) {
			indexBackMotor.set(0);
			ballUnbalancedSide = IntakeDirection.BACK;
		} else if (allSensorsOff()) {
			stopAll();
			ballUnbalancedSide = IntakeDirection.NONE;
		}
	}

//Switch ball positions

	public enum IntakeDirection {
		FRONT, BACK, BOTH, NONE;
	}

	public void intake(int num, IntakeDirection dir) {
		switch (num) {
		case 1:
			if (dir == IntakeDirection.FRONT) {
				indexFrontMotor.set(1);
			} else if (dir == IntakeDirection.BACK) {
				indexBackMotor.set(-1);
			}
			if (!mid.get()) {
				stopAll();
				numBalls++;
			}
			break;
		case 2:
			indexFrontMotor.set(1);
			indexBackMotor.set(-1);
			if (!frontMid.get()) {
				stopAll();
				ballUnbalancedSide = IntakeDirection.BACK;
				numBalls++;
			} else if (!backMid.get()) {
				stopAll();
				ballUnbalancedSide = IntakeDirection.FRONT;
				numBalls++;
			}
			break;
		case 3:
			if (ballUnbalancedSide == IntakeDirection.FRONT) {
				indexFrontMotor.set(1);
				if (!frontMid.get()) {
					stopAll();
					numBalls++;
					ballUnbalancedSide = IntakeDirection.NONE;

				} else if (!intakeBack.get()) {
					stopAll();
					numBalls++;
				}
			} else if (ballUnbalancedSide == IntakeDirection.BACK) {
				indexBackMotor.set(-1);
				if (!backMid.get()) {
					stopAll();
					numBalls++;
					ballUnbalancedSide = IntakeDirection.NONE;
				} else if (!intakeFront.get()) {
					stopAll();
					numBalls++;
				}

			}
			break;
		case 4:

			if (ballUnbalancedSide == IntakeDirection.FRONT) {
				indexFrontMotor.set(1);
				if (!frontMid.get()) {
					stopAll();
					numBalls++;
					ballUnbalancedSide = IntakeDirection.NONE;

				} else if (!intakeBack.get()) {
					stopAll();
					numBalls++;
				}
			} else if (ballUnbalancedSide == IntakeDirection.BACK) {
				indexBackMotor.set(-1);
				if (!backMid.get()) {
					stopAll();
					numBalls++;
					ballUnbalancedSide = IntakeDirection.NONE;
				} else if (!intakeFront.get()) {
					stopAll();
					numBalls++;
				}

			}
			break;
		case 5:
			if (dir == IntakeDirection.FRONT) {
				if (!intakeFront.get()) {
					numBalls++;
				}
			} else if (dir == IntakeDirection.BACK) {
				indexBackMotor.set(1);
				if (!intakeBack.get()) {
					numBalls++;
				}
			}
			break;
		}

	}

	public void swap(IntakeDirection dir, int num) {
		// dir is side with least balls
		switch (num) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			if (dir == IntakeDirection.FRONT) {
				if (!front.get()) {
					indexFrontMotor.set(0);
				} else {
					indexFrontMotor.set(-1);
				}
				if (!backMid.get() && !front.get()) {
					indexBackMotor.set(0);
					ballUnbalancedSide = IntakeDirection.BACK;
				} else {
					indexBackMotor.set(-1);
				}
			} else if (dir == IntakeDirection.BACK) {
				if (!back.get()) {
					indexBackMotor.set(0);
				} else {
					indexBackMotor.set(1);
				}
				if (!frontMid.get() && !back.get()) {
					indexFrontMotor.set(0);
					ballUnbalancedSide = IntakeDirection.FRONT;
				} else {
					indexFrontMotor.set(1);
				}
			}
			break;
		case 4:
			if (dir == IntakeDirection.FRONT) {
				indexFrontMotor.set(-1);
				indexBackMotor.set(-1);
				if (!front.get()) {
					stopAll();
					ballUnbalancedSide = IntakeDirection.BACK;
				}
			} else if (dir == IntakeDirection.BACK) {
				indexFrontMotor.set(1);
				indexBackMotor.set(1);
				if (!back.get()) {
					stopAll();
					ballUnbalancedSide = IntakeDirection.FRONT;
				}
			}
			break;
		case 5:
			break;
		}

	}
}