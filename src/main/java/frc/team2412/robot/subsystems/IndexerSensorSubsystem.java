package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSensorSubsystem extends SubsystemBase {

	private DigitalInput m_intakeFrontSensor, m_indexFrontSensor, m_indexFrontMidSensor, m_indexMidSensor,
			m_indexBackMidSensor, m_indexBackSensor, m_intakeBackSensor;

	private DigitalInput[] m_sensorArray;

	public IndexerSensorSubsystem(DigitalInput intakeFront, DigitalInput front, DigitalInput frontMid, DigitalInput mid,
			DigitalInput backMid, DigitalInput back, DigitalInput intakeBack) {
		m_intakeFrontSensor = intakeFront;
		m_indexFrontSensor = front;
		m_indexFrontMidSensor = frontMid;
		m_indexMidSensor = mid;
		m_indexBackMidSensor = backMid;
		m_indexBackSensor = back;
		m_intakeBackSensor = intakeBack;

		m_sensorArray = new DigitalInput[] { m_indexFrontSensor, m_indexFrontMidSensor, m_indexMidSensor,
				m_indexBackMidSensor, m_indexBackSensor };

	}

	public boolean getIntakeFrontSensorValue() {
		return !m_intakeFrontSensor.get();
	}

	public boolean getIndexFrontSensorValue() {
		return !m_indexFrontSensor.get();
	}

	public boolean getIndexFrontMidSensorValue() {
		return !m_indexFrontMidSensor.get();
	}

	public boolean getIndexMidSensorValue() {
		return !m_indexMidSensor.get();
	}

	public boolean getIndexBackMidSensorValue() {
		return !m_indexBackMidSensor.get();
	}

	public boolean getIndexBackSensorValue() {
		return !m_indexBackSensor.get();
	}

	public boolean getIntakeBackSensorValue() {
		return !m_intakeBackSensor.get();
	}

	public boolean allInnerSensorsOff() {
		if (!m_indexFrontSensor.get() && !m_indexFrontMidSensor.get() && !m_indexMidSensor.get()
				&& !m_indexBackMidSensor.get() && !m_intakeBackSensor.get()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean allInnerSensorsOn() {
		if (m_indexFrontSensor.get() && m_indexFrontMidSensor.get() && m_indexMidSensor.get()
				&& m_indexBackMidSensor.get() && m_intakeBackSensor.get()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean allFrontSensorsOff() {
		if (!m_intakeFrontSensor.get() && !m_indexFrontSensor.get() && !m_indexFrontMidSensor.get()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean allBackSensorsOff() {
		if (!m_intakeBackSensor.get() && !m_indexBackSensor.get() && !m_indexBackMidSensor.get()) {
			return true;
		} else {
			return false;
		}
	}

	public int totalSensorsOn() {
		int total = 0;
		for (DigitalInput d : m_sensorArray) {
			if (!d.get()) {
				total++;
			}
		}
		return total;
	}

}
