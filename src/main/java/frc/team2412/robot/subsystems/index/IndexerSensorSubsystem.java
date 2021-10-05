package frc.team2412.robot.subsystems.index;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSensorSubsystem extends SubsystemBase {

	private DigitalInput m_indexBackInnerSensor;
	private DigitalInput m_indexBackMidSensor;
	private DigitalInput m_indexBackSensor;
	private DigitalInput m_indexFrontInnerSensor;
	private DigitalInput m_indexFrontMidSensor;
	private DigitalInput m_indexFrontSensor;
	private DigitalInput m_intakeBackSensor;
	private DigitalInput m_intakeFrontSensor;
	private Map<DigitalInput, Integer> sensorMap;

	private DigitalInput[] m_sensorArray;

	public IndexerSensorSubsystem(DigitalInput intakeFront, DigitalInput front, DigitalInput frontMid,
			DigitalInput frontInner, DigitalInput backInner, DigitalInput backMid, DigitalInput back,
			DigitalInput intakeBack) {
		m_intakeFrontSensor = intakeFront;
		m_indexFrontSensor = front;
		m_indexFrontMidSensor = frontMid;
		m_indexFrontInnerSensor = frontInner;
		m_indexBackInnerSensor = backInner;
		m_indexBackMidSensor = backMid;
		m_indexBackSensor = back;
		m_intakeBackSensor = intakeBack;

		sensorMap = new HashMap<>();
		// sensorMap.put(front, 0);
		// sensorMap.put(frontMid, 0);
		// sensorMap.put(frontInner, 0);
		// sensorMap.put(back, 0);
		// sensorMap.put(backMid, 0);
		// sensorMap.put(backInner, 0);

		m_sensorArray = new DigitalInput[] { m_indexFrontSensor, m_indexFrontMidSensor, m_indexFrontInnerSensor,
				m_indexBackInnerSensor, m_indexBackMidSensor, m_indexBackSensor };

	}

	public boolean allBackSensorsOff() {
		return !getIndexBackSensorValue() && !getIndexBackMidSensorValue() && !getIndexBackInnerSensorValue();
		//return !m_indexBackSensor.get() && !m_indexBackMidSensor.get() && !m_indexBackInnerSensor.get();
	}

	public boolean allFrontSensorsOff() {
		return !getIndexFrontSensorValue() && !getIndexFrontMidSensorValue() && !getIndexFrontInnerSensorValue();

		//return !m_indexFrontSensor.get() && !m_indexFrontMidSensor.get() && !m_indexFrontInnerSensor.get();
	}
	public boolean allBackSensorsOn() {
		return getIndexBackSensorValue() && getIndexBackMidSensorValue() && getIndexBackInnerSensorValue();

		//return m_indexBackSensor.get() && m_indexBackMidSensor.get() && m_indexBackInnerSensor.get();
	}

	public boolean allFrontSensorsOn() {
		return getIndexFrontSensorValue() && getIndexFrontMidSensorValue() && getIndexFrontInnerSensorValue();

		//return m_indexFrontSensor.get() && m_indexFrontMidSensor.get() && m_indexFrontInnerSensor.get();
	}

	public boolean allInnerSensorsOff() {
		for (int i = 1; i < m_sensorArray.length-1; i++) {
			if (!m_sensorArray[i].get()) {
				return false;
			}
		}
		return true;
	}

	public boolean allInnerSensorsOn() {
		for (int i = 1; i < m_sensorArray.length-1; i++) {
			if (m_sensorArray[i].get()) {
				return false;
			}
		}
		return true;
	}

	public boolean getIndexBackInnerSensorValue() {
		return getVal(m_indexBackInnerSensor);// !m_indexBackInnerSensor.get();
	}

	public boolean getIndexBackMidSensorValue() {
		return getVal(m_indexBackMidSensor);//!m_indexBackMidSensor.get();
	}

	public boolean getIndexBackSensorValue() {
		return getVal(m_indexBackSensor);//!m_indexBackSensor.get();
	}

	public boolean getIndexFrontInnerSensorValue() {
		return getVal(m_indexFrontInnerSensor);//!m_indexFrontInnerSensor.get();
	}

	public boolean getIndexFrontMidSensorValue() {
		return getVal(m_indexFrontMidSensor);//!m_indexFrontMidSensor.get();
	}

	public boolean getIndexFrontSensorValue() {
		return getVal(m_indexFrontSensor);//!m_indexFrontSensor.get();
	}

	public boolean getIntakeBackSensorValue() {
		return !m_intakeBackSensor.get();
	}

	public boolean getIntakeFrontSensorValue() {
		return !m_intakeFrontSensor.get();
	}

	private boolean getVal(DigitalInput i){
		return !i.get();
		// int e = !i.get() ? sensorMap.getOrDefault(i, 0)+1: 0;
		// sensorMap.put(i, e);
		// return e > 10;

	}

	// Get the sensor states as a bitmap using the back side as the least
	// significant bit
	public int getSensorBitmapBackLSB() {
		return (getIndexBackSensorValue() ? 0x01 : 0) | (getIndexBackMidSensorValue() ? 0x02 : 0)
				| (getIndexBackInnerSensorValue() ? 0x04 : 0) | (getIndexFrontInnerSensorValue() ? 0x08 : 0)
				| (getIndexFrontMidSensorValue() ? 0x10 : 0) | (getIndexFrontSensorValue() ? 0x20 : 0);
	}

	// Get the sensor states as a bitmap using the front side as the least
	// significant bit
	public int getSensorBitmapFrontLSB() {
		return (getIndexFrontSensorValue() ? 0x01 : 0) | (getIndexFrontMidSensorValue() ? 0x02 : 0)
				| (getIndexFrontInnerSensorValue() ? 0x04 : 0) | (getIndexBackInnerSensorValue() ? 0x08 : 0)
				| (getIndexBackMidSensorValue() ? 0x10 : 0) | (getIndexBackSensorValue() ? 0x20 : 0);
	}

	// @Log.Dial(tabName = "Indexer", min = 0, max = 8)
	public int totalSensorsOn() {
		int total = 0;
		for (int i = 0; i < m_sensorArray.length; i++) {
			if (!m_sensorArray[i].get()) {
				// TO DEAL WITH 2 CENTER SENSORS
				if (i == 2) {
					i++;
				}
				total++;
			}
		}
		return total;
	}

}
