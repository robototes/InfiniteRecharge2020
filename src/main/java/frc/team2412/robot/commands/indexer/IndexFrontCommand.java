package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexFrontCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerSensorSubsystem m_indexerSensorSubsystem;

	public IndexFrontCommand(IndexerMotorSubsystem motorSubsystem, IndexerSensorSubsystem indexerSensorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_indexerSensorSubsystem = indexerSensorSubsystem;
		addRequirements(motorSubsystem, indexerSensorSubsystem);
	}

	@Override
	public void execute() {
		if(!m_indexerSensorSubsystem.isIndexBackSensorTripped() && !m_indexerSensorSubsystem.isIndexFrontSensorTripped()) {
			m_indexerMotorSubsystem.setFrontMotor(0);
			m_indexerMotorSubsystem.setBackMotor(0);
		}
		
		
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
