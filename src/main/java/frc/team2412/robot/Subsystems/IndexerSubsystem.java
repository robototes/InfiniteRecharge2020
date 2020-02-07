package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IndexerConstants.IndexerDirection;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerSubsystem extends SubsystemBase implements Loggable {
	@Log
	public CANSparkMax m_indexBackMotor, m_indexFrontMotor, m_indexMidMotor;
	public SpeedControllerGroup m_indexMotors, m_indexSideMotors;
	@Log(name = "Indexer Sensor")
	public DigitalInput m_back, m_backMid, m_mid, m_frontMid, m_front, m_intakeFront, m_intakeBack;

	@Log
	// NUMBER OF BALLS IN SYSTEM
	private int m_numBalls = 0;

	// SIDE OF INDEXER WITH LESS BALLS
	@Log
	private IndexerDirection m_ballUnbalancedSide;

	public IndexerSubsystem(CANSparkMax frontm, CANSparkMax middle, CANSparkMax backm, DigitalInput f, DigitalInput fm,
			DigitalInput m, DigitalInput bm, DigitalInput b, DigitalInput inf, DigitalInput inb) {
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
	}

	// Motors
	@Log
	public void stopAll() {
		m_indexMotors.set(0.0);
	}

	public void puke() {
		m_indexMotors.set(-1);
		m_indexSideMotors.set(-1);
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

	@Config
	public void intake(int balls, IndexerDirection dir) {
		switch (balls) {
		case 1:
			intake1(dir);
			break;
		case 2:
			intake2(dir);
			break;
		case 3:
			intake3(dir);
			break;
		case 4:
			intake4(dir);
			break;
		case 5:
			intake5(dir);
			break;
		}
	}

	public void swap(int balls, IndexerDirection dir) {
		switch (balls) {
		case 3:
			swap3(dir);
			break;
		case 4:
			swap4(dir);
			break;
		}
	}

	@Log
	public void shoot() {
		// allShiftDown();
		m_indexMidMotor.set(1);
		if (getBallUnbalancedSide() == IndexerDirection.FRONT) {
			m_indexBackMotor.set(-1);
		} else {
			m_indexFrontMotor.set(1);
		}
		if (m_frontMid.get() && m_front.get()) {
			m_indexFrontMotor.set(0);
			setBallUnbalancedSide(IndexerDirection.FRONT);
		} else if (m_backMid.get() && m_back.get()) {
			m_indexBackMotor.set(0);
			setBallUnbalancedSide(IndexerDirection.BACK);
		} else if (allSensorsOff()) {
			stopAll();
			setBallUnbalancedSide(IndexerDirection.NONE);
		}
	}

//Switch ball positions
	public void intake1(IndexerDirection dir) {
		if (dir == IndexerDirection.FRONT) {
			m_indexFrontMotor.set(1);
		} else if (dir == IndexerDirection.BACK) {
			m_indexBackMotor.set(-1);
		}
		if (!m_mid.get()) {
			stopAll();
			setNumBalls(getNumBalls() + 1);
		}
	}

	public void intake2(IndexerDirection dir) {
		m_indexFrontMotor.set(1);
		m_indexBackMotor.set(-1);
		if (!m_frontMid.get()) {
			stopAll();
			setBallUnbalancedSide(IndexerDirection.BACK);
			setNumBalls(getNumBalls() + 1);
		} else if (!m_backMid.get()) {
			stopAll();
			setBallUnbalancedSide(IndexerDirection.FRONT);
			setNumBalls(getNumBalls() + 1);
		}
	}

	public void intake3(IndexerDirection dir) {
		if (getBallUnbalancedSide() == IndexerDirection.FRONT) {
			m_indexFrontMotor.set(1);
			if (!m_frontMid.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
				setBallUnbalancedSide(IndexerDirection.NONE);

			} else if (!m_intakeBack.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
			}
		} else if (getBallUnbalancedSide() == IndexerDirection.BACK) {
			m_indexBackMotor.set(-1);
			if (!m_backMid.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
				setBallUnbalancedSide(IndexerDirection.NONE);
			} else if (!m_intakeFront.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
			}

		}
	}

	public void intake4(IndexerDirection dir) {
		if (getBallUnbalancedSide() == IndexerDirection.FRONT) {
			m_indexFrontMotor.set(1);
			if (!m_frontMid.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
				setBallUnbalancedSide(IndexerDirection.NONE);

			} else if (!m_intakeBack.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
			}
		} else if (getBallUnbalancedSide() == IndexerDirection.BACK) {
			m_indexBackMotor.set(-1);
			if (!m_backMid.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
				setBallUnbalancedSide(IndexerDirection.NONE);
			} else if (!m_intakeFront.get()) {
				stopAll();
				setNumBalls(getNumBalls() + 1);
			}

		}

	}

	public void intake5(IndexerDirection dir) {
		if (dir == IndexerDirection.FRONT) {
			if (!m_intakeFront.get()) {
				setNumBalls(getNumBalls() + 1);
			}
		} else if (dir == IndexerDirection.BACK) {
			m_indexBackMotor.set(1);
			if (!m_intakeBack.get()) {
				setNumBalls(getNumBalls() + 1);
			}
		}
	}

	public void swap3(IndexerDirection dir) {
		if (dir == IndexerDirection.FRONT) {
			if (!m_front.get()) {
				m_indexFrontMotor.set(0);
			} else {
				m_indexFrontMotor.set(-1);
			}
			if (!m_backMid.get() && !m_front.get()) {
				m_indexBackMotor.set(0);
				setBallUnbalancedSide(IndexerDirection.BACK);
			} else {
				m_indexBackMotor.set(-1);
			}
		} else if (dir == IndexerDirection.BACK) {
			if (!m_back.get()) {
				m_indexBackMotor.set(0);
			} else {
				m_indexBackMotor.set(1);
			}
			if (!m_frontMid.get() && !m_back.get()) {
				m_indexFrontMotor.set(0);
				setBallUnbalancedSide(IndexerDirection.FRONT);
			} else {
				m_indexFrontMotor.set(1);
			}
		}
	}

	public void swap4(IndexerDirection dir) {
		if (dir == IndexerDirection.FRONT) {
			m_indexFrontMotor.set(-1);
			m_indexBackMotor.set(-1);
			if (!m_front.get()) {
				stopAll();
				setBallUnbalancedSide(IndexerDirection.BACK);
			}
		} else if (dir == IndexerDirection.BACK) {
			m_indexFrontMotor.set(1);
			m_indexBackMotor.set(1);
			if (!m_back.get()) {
				stopAll();
				setBallUnbalancedSide(IndexerDirection.FRONT);
			}
		}
	}

	public IndexerDirection getBallUnbalancedSide() {
		return m_ballUnbalancedSide;
	}

	@Config
	public void setBallUnbalancedSide(IndexerDirection m_ballUnbalancedSide) {
		this.m_ballUnbalancedSide = m_ballUnbalancedSide;
	}

	public int getNumBalls() {
		return m_numBalls;
	}

	@Config
	public void setNumBalls(int m_numBalls) {
		this.m_numBalls = m_numBalls;
	}
}