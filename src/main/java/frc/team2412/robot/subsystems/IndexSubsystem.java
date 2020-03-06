package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;

public class IndexSubsystem extends SubsystemBase implements Loggable {

	private IndexSideSubsystem m_frontSide;
	private IndexSideSubsystem m_backSide;
	private LiftMotorSubsystem m_middleMotor;
	private IndexerSensorSubsystem m_sensors;	
	
	public IndexSubsystem(IndexSideSubsystem frontSide, IndexSideSubsystem backSide, LiftMotorSubsystem middleMotor,
			IndexerSensorSubsystem sensors) {
		m_frontSide = frontSide;
		m_backSide = backSide;
		m_middleMotor = middleMotor;
		m_sensors = sensors;
	}

}
