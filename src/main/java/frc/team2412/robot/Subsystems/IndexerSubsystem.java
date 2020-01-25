package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class IndexerSubsystem extends SubsystemBase {
	public CANSparkMax indexBackMotor;
	public CANSparkMax indexFrontMotor;
	public CANSparkMax indexMidMotor;
	public SpeedControllerGroup indexMotors;
	public SpeedControllerGroup indexSideMotors;

	public DigitalInput back;
	public DigitalInput backMid;
	public DigitalInput mid;
	public DigitalInput frontMid;
	public DigitalInput front;
	
	public DoubleSolenoid frontClutch;
	public DoubleSolenoid rearClutch;

	public int numBalls = 0;
	public boolean ballUnbalancedSide = false;

	public IndexerSubsystem() {
		// Motors
		indexBackMotor = RobotMap.indexFrontMotor;
		indexFrontMotor = RobotMap.indexBackMotor;
		indexMidMotor = RobotMap.indexMidMotor;
		indexMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor, indexMidMotor);
		indexSideMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor);

		// Sensors
		back = RobotMap.BB;
		backMid = RobotMap.BT;
		mid = RobotMap.T;
		frontMid = RobotMap.FT;
		front = RobotMap.FF;
		
		//soulenoid
		frontClutch = RobotMap.frontClutch;
		rearClutch = RobotMap.rearClutch;
	}

	public void indexerLogic(boolean shoot) {
		if (shoot) {
			shoot();
		} else {
			switch (numBalls) {
			case 0:
				intake1();
			case 1:
				intake2();
				switchBalls2(ballUnbalancedSide);
			case 2:
				intake3();
				switchBalls34(ballUnbalancedSide);
			case 3:
				intake4();
				switchBalls34(ballUnbalancedSide);
			case 4:
				intake5();
			case 5:
				System.out.println("im full");
			}
		}

	}
	//shifters
	public void frontShiftUp() {
		frontClutch.set(DoubleSolenoid.Value.kForward);
	}
	public void frontShiftDown() {
		frontClutch.set(DoubleSolenoid.Value.kReverse);
	}
	public void backShiftUp() {
		rearClutch.set(DoubleSolenoid.Value.kForward);
	}
	public void backShiftDown() {
		rearClutch.set(DoubleSolenoid.Value.kReverse);
	}
	public void allShiftUp() {
		rearClutch.set(DoubleSolenoid.Value.kForward);
		frontClutch.set(DoubleSolenoid.Value.kForward);
	}
	public void allShiftDown() {
		rearClutch.set(DoubleSolenoid.Value.kReverse);
		frontClutch.set(DoubleSolenoid.Value.kReverse);
	}
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

	/*
	 * if(allSensorsOff()){intake12();
	 * 
	 * boolean b;if(backMid.get()) { b = true; } else { b = false; }
	 * switchBalls2(b); intake3(); if (backMid.get()) { b = true; } else { b =
	 * false; } switchBalls34(b); intake4(); if (back.get()) { b = true; } else { b
	 * = false; } switchBalls34(b); intake5(); }
	 * 
	 * shoot();
	 * 
	 * }
	 */
	public void shoot() {
		allShiftDown();
		indexMidMotor.set(1);
		if (frontMid.get() && front.get()) {
			indexFrontMotor.set(0);
		} else if (backMid.get() && back.get()) {
			indexBackMotor.set(0);
		}
		
		stopAll();
	}

//Switch ball positions
	public void switchBalls2(boolean currentDirection) {
		if (currentDirection != (DriveBaseSubsystem.getCurrentYSpeed >= 0)) {
			if (currentDirection) {
				indexFrontMotor.set(1);
				indexBackMotor.set(1);
				if (!backMid.get()) {
					stopAll();
					ballUnbalancedSide = !ballUnbalancedSide;
				}
			} else {
				indexFrontMotor.set(-1);
				indexBackMotor.set(-1);
				if (!frontMid.get()) {
					stopAll();
					ballUnbalancedSide = !ballUnbalancedSide;
				}
			}
		}
	}

	public void switchBalls34(boolean currentDirection) {
		if (currentDirection != (DriveBaseSubsystem.getCurrentYSpeed >= 0)) {
			if (currentDirection) {
				indexFrontMotor.set(1);
				indexBackMotor.set(1);
				if (!back.get()) {
					stopAll();
					ballUnbalancedSide = !ballUnbalancedSide;
				}
			} else {
				indexFrontMotor.set(-1);
				indexBackMotor.set(-1);
				if (!front.get()) {
					stopAll();
					ballUnbalancedSide = !ballUnbalancedSide;
				}
			}
		}
	}

//first two balls
	public void intake1() {

		indexFrontMotor.set(1);
		indexBackMotor.set(-1);
		if (!mid.get()) {
			stopAll();
			numBalls++;
		}
	}

	public void intake2() {
		indexFrontMotor.set(1);
		indexBackMotor.set(-1);
		if (!frontMid.get()) {
			stopAll();
			ballUnbalancedSide = false;
			numBalls++;
		} else if (!backMid.get()) {
			stopAll();
			ballUnbalancedSide = true;
			numBalls++;
		}
	}

//Third Ball 
	private int run = 0;
	private boolean intakeDirection;

	public void intake3() {
		if (run == 0) {
			intakeDirection = (DriveBaseSubsystem.getCurrentYSpeed >= 0);
			run++;
		}
		if (intakeDirection) {
			if (!frontMid.get()) {
				indexFrontMotor.set(0);
			} else {
				indexFrontMotor.set(1);

			}
			if (!back.get()) {
				indexBackMotor.set(0);
			} else {
				indexBackMotor.set(1);
			}
			if (!back.get() && !backMid.get() && mid.get() && frontMid.get() && front.get()) {
				numBalls++;
				run--;
			}
		} else {
			if (!backMid.get()) {
				indexFrontMotor.set(0);
			} else {
				indexFrontMotor.set(-1);

			}
			if (!front.get()) {
				indexBackMotor.set(0);
			} else {
				indexBackMotor.set(-1);
			}
			if (back.get() && backMid.get() && mid.get() && !frontMid.get() && !front.get()) {
				numBalls++;
				run--;
			}
		}
	}

//fourth ball
	public void intake4() {
		if (run == 0) {
			intakeDirection = (DriveBaseSubsystem.getCurrentYSpeed >= 0);
			run++;
		}
		if (intakeDirection) {
			indexFrontMotor.set(1);
			if (!frontMid.get()) {
				indexFrontMotor.set(0);
				numBalls++;
				run--;
				ballUnbalancedSide = true;
			}
		} else {
			indexBackMotor.set(-1);
			if (!backMid.get()) {
				indexBackMotor.set(0);
				numBalls++;
				run--;
				ballUnbalancedSide = false;
			}
		}
	}

	public void intake5() {
		if (run == 0) {
			intakeDirection = (DriveBaseSubsystem.getCurrentYSpeed >= 0);
			run++;
		}
		allShiftUp();
		if (intakeDirection) {
			indexFrontMotor.set(1);
			if (!front.get()) {
				indexFrontMotor.set(0);
				numBalls++;
				run--;
			}
		} else {
			indexBackMotor.set(-1);
			if (!back.get()) {
				indexBackMotor.set(0);
				numBalls++;
				run--;
			}
		}
	}
}