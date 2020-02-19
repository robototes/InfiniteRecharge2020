package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import static frc.team2412.robot.subsystems.constants.IndexerConstants.numBalls;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexSpitCommand extends CommandBase {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexSpitCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;

		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(1);
		m_indexerMotorSubsystem.setBackMotor(1);
		m_indexerMotorSubsystem.setMidMotor(-0.1);
	}

	@Override
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.allInnerSensorsOff()) {
			m_indexerMotorSubsystem.stopAllMotors();
			numBalls = 0;
			return true;
		} else {
			return false;
		}

	}

}
