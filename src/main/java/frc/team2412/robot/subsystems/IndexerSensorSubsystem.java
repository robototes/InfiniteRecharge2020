package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerSensorSubsystem extends SubsystemBase implements Loggable {

	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_intakeFrontSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexFrontSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexFrontMidSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexFrontInnerSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexBackInnerSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexBackMidSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_indexBackSensor;
	@Log.BooleanBox(tabName = "Indexer", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "get")
	private DigitalInput m_intakeBackSensor;

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

		m_sensorArray = new DigitalInput[] { m_indexFrontSensor, m_indexFrontMidSensor, m_indexFrontInnerSensor,
				m_indexBackInnerSensor, m_indexBackMidSensor, m_indexBackSensor };

	}

	public boolean isIntakeFrontSensorTripped() {
		return !m_intakeFrontSensor.get();
	}

	public boolean isIndexFrontSensorTripped() {
		return !m_indexFrontSensor.get();
	}

	public boolean isIndexFrontMidSensorTripped() {
		return !m_indexFrontMidSensor.get();
	}

	public boolean isIndexFrontInnerSensorTripped() {
		return !m_indexFrontInnerSensor.get();
	}

	public boolean isIndexBackInnerSensorTripped() {
		return !m_indexBackInnerSensor.get();
	}

	public boolean isIndexBackMidSensorTripped() {
		return !m_indexBackMidSensor.get();
	}

	public boolean isIndexBackSensorTripped() {
		return !m_indexBackSensor.get();
	}

	public boolean isIntakeBackSensorTripped() {
		return !m_intakeBackSensor.get();
	}

	public boolean allInnerSensorsOff() {
		for (DigitalInput d : m_sensorArray) {
			if (!d.get()) {
				return false;
			}
		}
		return true;
	}

	public boolean allInnerSensorsOn() {
		for (DigitalInput d : m_sensorArray) {
			if (d.get()) {
				return false;
			}
		}
		return true;
	}

	public boolean allFrontSensorsOff() {
		return !m_intakeFrontSensor.get() && !m_indexFrontSensor.get() && !m_indexFrontMidSensor.get()
				&& !m_indexFrontInnerSensor.get();
	}

	public boolean allBackSensorsOff() {
		return !m_intakeBackSensor.get() && !m_indexBackSensor.get() && !m_indexBackMidSensor.get()
				&& !m_indexBackInnerSensor.get();
	}
	public boolean allSensorsOn(){
		return !m_intakeBackSensor.get() && !m_indexBackSensor.get() && !m_indexBackMidSensor.get()
		&& !m_indexBackInnerSensor.get() && !m_intakeFrontSensor.get() && !m_indexFrontSensor.get() && !m_indexFrontMidSensor.get()
		&& !m_indexFrontInnerSensor.get();
	}

	// Get the sensor states as a bitmap using the back side as the least significant bit
	public int getSensorBitmapBackLSB() {
		return (getIndexBackSensorValue() ? 0x01 : 0) |
			(getIndexBackMidSensorValue() ? 0x02 : 0) |
			(getIndexBackInnerSensorValue() ? 0x04 : 0) |
			(getIndexFrontInnerSensorValue() ? 0x08 : 0) |
			(getIndexFrontMidSensorValue() ? 0x10 : 0) |
			(getIndexFrontSensorValue() ? 0x20 : 0);
	}

	// Get the sensor states as a bitmap using the front side as the least significant bit
	public int getSensorBitmapFrontLSB() {
		return (getIndexFrontSensorValue() ? 0x01 : 0) |
			(getIndexFrontMidSensorValue() ? 0x02 : 0) |
			(getIndexFrontInnerSensorValue() ? 0x04 : 0) |
			(getIndexBackInnerSensorValue() ? 0x08 : 0) |
			(getIndexBackMidSensorValue() ? 0x10 : 0) |
			(getIndexBackSensorValue() ? 0x20 : 0);
	}

	@Log.Dial(tabName = "Indexer", min = 0, max = 8)
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

	public int binaryValueOfSensors() {
		int total = 0;

		total += isIndexFrontSensorTripped() ? 1 : 0;
		total += isIndexFrontMidSensorTripped() ? 2 : 0;
		total += isIndexFrontInnerSensorTripped() ? 4 : 0;
		total += isIndexBackInnerSensorTripped() ? 8 : 0;
		total += isIndexBackMidSensorTripped() ? 16 : 0;
		total += isIndexBackSensorTripped() ? 32 : 0;

		return total;
	}
	
	public boolean allSensorsOff() {
		return allBackSensorsOff() && allFrontSensorsOff() && allInnerSensorsOff();
	}

}
