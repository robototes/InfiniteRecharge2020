package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Commands.IndexerCommands.IntakeBalls;
import frc.team2412.robot.Commands.IndexerCommands.ProcessBalls;
import frc.team2412.robot.Commands.IndexerCommands.ShootBalls;
import frc.team2412.robot.Commands.IndexerCommands.SwitchBalls;
import frc.team2412.robot.Subsystems.constants.IndexerConstants.IntakeDirection;

public class IndexerSubsystem extends SubsystemBase {
	public CANSparkMax m_indexBackMotor, m_indexFrontMotor, m_indexMidMotor;
	public SpeedControllerGroup m_indexMotors, m_indexSideMotors;
	public DigitalInput m_back, m_backMid, m_mid, m_frontMid, m_front, m_intakeFront, m_intakeBack;

	// NUMBER OF BALLS IN SYSTEM
	private int m_numBalls = 0;

	// SIDE OF INDEXER WITH LESS BALLS
	private IntakeDirection m_ballUnbalancedSide;


	public IndexerSubsystem(CANSparkMax frontm, CANSparkMax middle, CANSparkMax backm, DigitalInput f, DigitalInput fm, DigitalInput m, DigitalInput bm, DigitalInput b, DigitalInput inf, DigitalInput inb) {
		// Motors
		m_indexBackMotor = backm;
		m_indexFrontMotor = frontm;
		m_indexMidMotor = middle;
		m_indexMotors = new SpeedControllerGroup(frontm, middle, backm);
		m_indexSideMotors = new SpeedControllerGroup(frontm, backm);

		// Sensors
		m_back = b;
		m_backMid = bm;
		m_mid = m;
		m_frontMid = fm;
		m_front = f;
		
		m_intakeFront = inf;
		m_intakeBack = inb;
		
		setDefaultCommand(new ProcessBalls(this, new ShootBalls(this), new IntakeBalls(this), new SwitchBalls(this)));
	}

	// shifters

	// Motors
	public void stopAll() {
		m_indexMotors.set(0.0);
	}

	public void stopSides() {
		m_indexSideMotors.set(0.0);
	}

	// Sensors
	public boolean allSensorsOn() {
		if (m_back.get() && m_backMid.get() && m_mid.get() && m_frontMid.get() && m_front.get()) {
			return true;
		}

		return false;
		// check if all sensors are on
	}

	public boolean allSensorsOff() {
		if (!m_back.get() && !m_backMid.get() && !m_mid.get() && !m_frontMid.get() && !m_front.get()) {
			return true;
		}

		return false;
		// check if all sensors are on
	}

	public void shoot() {
		// allShiftDown();
		m_indexMidMotor.set(1);
		if (getBallUnbalancedSide() == IntakeDirection.FRONT) {
			m_indexBackMotor.set(-1);
		} else {
			m_indexFrontMotor.set(1);
		}
		if (m_frontMid.get() && m_front.get()) {
			m_indexFrontMotor.set(0);
			setBallUnbalancedSide(IntakeDirection.FRONT);
		} else if (m_backMid.get() && m_back.get()) {
			m_indexBackMotor.set(0);
			setBallUnbalancedSide(IntakeDirection.BACK);
		} else if (allSensorsOff()) {
			stopAll();
			setBallUnbalancedSide(IntakeDirection.NONE);
		}
	}

//Switch ball positions

	public void intake(int num, IntakeDirection dir) {
		switch (num) {
		case 1:
			if (dir == IntakeDirection.FRONT) {
				m_indexFrontMotor.set(1);
			} else if (dir == IntakeDirection.BACK) {
				m_indexBackMotor.set(-1);
			}
			if (!m_mid.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
			}
			break;
		case 2:
			m_indexFrontMotor.set(1);
			m_indexBackMotor.set(-1);
			if (!m_frontMid.get()) {
				stopAll();
				setBallUnbalancedSide(IntakeDirection.BACK);
				setNumBalls(getNumBalls() + 1);
			} else if (!m_backMid.get()) {
				stopAll();
				setBallUnbalancedSide(IntakeDirection.FRONT);
				setNumBalls(getNumBalls() + 1);
			}
			break;
		case 3:
			if (getBallUnbalancedSide() == IntakeDirection.FRONT) {
				m_indexFrontMotor.set(1);
				if (!m_frontMid.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
					setBallUnbalancedSide(IntakeDirection.NONE);

				} else if (!m_intakeBack.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
				}
			} else if (getBallUnbalancedSide() == IntakeDirection.BACK) {
				m_indexBackMotor.set(-1);
				if (!m_backMid.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
					setBallUnbalancedSide(IntakeDirection.NONE);
				} else if (!m_intakeFront.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
				}

			}
			break;
		case 4:

			if (getBallUnbalancedSide() == IntakeDirection.FRONT) {
				m_indexFrontMotor.set(1);
				if (!m_frontMid.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
					setBallUnbalancedSide(IntakeDirection.NONE);

				} else if (!m_intakeBack.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
				}
			} else if (getBallUnbalancedSide() == IntakeDirection.BACK) {
				m_indexBackMotor.set(-1);
				if (!m_backMid.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
					setBallUnbalancedSide(IntakeDirection.NONE);
				} else if (!m_intakeFront.get()) {
					stopAll();
					setNumBalls(getNumBalls() + 1);
				}

			}
			break;
		case 5:
			if (dir == IntakeDirection.FRONT) {
				if (!m_intakeFront.get()) {
					setNumBalls(getNumBalls() + 1);
				}
			} else if (dir == IntakeDirection.BACK) {
				m_indexBackMotor.set(1);
				if (!m_intakeBack.get()) {
					setNumBalls(getNumBalls() + 1);
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
				if (!m_front.get()) {
					m_indexFrontMotor.set(0);
				} else {
					m_indexFrontMotor.set(-1);
				}
				if (!m_backMid.get() && !m_front.get()) {
					m_indexBackMotor.set(0);
					setBallUnbalancedSide(IntakeDirection.BACK);
				} else {
					m_indexBackMotor.set(-1);
				}
			} else if (dir == IntakeDirection.BACK) {
				if (!m_back.get()) {
					m_indexBackMotor.set(0);
				} else {
					m_indexBackMotor.set(1);
				}
				if (!m_frontMid.get() && !m_back.get()) {
					m_indexFrontMotor.set(0);
					setBallUnbalancedSide(IntakeDirection.FRONT);
				} else {
					m_indexFrontMotor.set(1);
				}
			}
			break;
		case 4:
			if (dir == IntakeDirection.FRONT) {
				m_indexFrontMotor.set(-1);
				m_indexBackMotor.set(-1);
				if (!m_front.get()) {
					stopAll();
					setBallUnbalancedSide(IntakeDirection.BACK);
				}
			} else if (dir == IntakeDirection.BACK) {
				m_indexFrontMotor.set(1);
				m_indexBackMotor.set(1);
				if (!m_back.get()) {
					stopAll();
					setBallUnbalancedSide(IntakeDirection.FRONT);
				}
			}
			break;
		case 5:
			break;
		}

	}

	public IntakeDirection getBallUnbalancedSide() {
		return m_ballUnbalancedSide;
	}

	public void setBallUnbalancedSide(IntakeDirection m_ballUnbalancedSide) {
		this.m_ballUnbalancedSide = m_ballUnbalancedSide;
	}

	public int getNumBalls() {
		return m_numBalls;
	}

	public void setNumBalls(int m_numBalls) {
		this.m_numBalls = m_numBalls;
	}
}