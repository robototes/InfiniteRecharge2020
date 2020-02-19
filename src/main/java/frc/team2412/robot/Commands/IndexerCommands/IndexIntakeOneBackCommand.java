package frc.team2412.robot.Commands.IndexerCommands;

import static frc.team2412.robot.Subsystems.constants.IndexerConstants.numBalls;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeOneBackCommand extends CommandBase {
	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexIntakeOneBackCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setBackMotor(-1);
	}

	@Override
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.getIndexMidSensorValue()) {
			m_indexerMotorSubsystem.stopBackPID();
			numBalls++;
			return true;
		} else {
			return false;
		}
	}

}
