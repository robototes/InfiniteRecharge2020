package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexPulseCommand extends WaitCommand {

	IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

	private static double PULSE_TIME = 0.5;

	public IndexPulseCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		super(PULSE_TIME);
		this.m_IndexerSubsystemSuperStructure = indexerSubsystemSuperStructure;
	}

	@Override
	public void initialize() {
		super.initialize();
		this.m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().in();
		this.m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().in();
	}

	@Override
	public void execute() {
		super.execute();
		if (this.m_timer.hasElapsed(PULSE_TIME / 2)) {
			this.m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().in();
			this.m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().stop();
		}
	}

	@Override
	public void end(boolean cancel) {
		super.end(cancel);
		this.m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().stop();
		this.m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().stop();
	}

}
