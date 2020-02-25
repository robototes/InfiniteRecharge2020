package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeOneFrontCommand extends CommandBase {
	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexIntakeOneFrontCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		if (m_indexerSensorSubsystem.getIntakeFrontSensorValue())
			m_indexerMotorSubsystem.setFrontMotor(-1);
	}
	@Override
	public void end(boolean cancel){
		RobotState.m_ballCount++;
		m_indexerMotorSubsystem.stopFrontPID(IndexerConstants.EXTRA_LONG_STOP_DISTANCE);
	}
	@Override
	public boolean isFinished() {
		// System.out.println("pppp");
		return m_indexerSensorSubsystem.getIndexFrontMidSensorValue();
	}

}
