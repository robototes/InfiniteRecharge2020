package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexAllOutCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;

	public IndexAllOutCommand(IndexerSubsystemSuperStructure motorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().out();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
