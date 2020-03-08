package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShiftBackCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_indexSubsystemSuperStructure;

	private double startPosition;

	public IndexShiftBackCommand(IndexerSubsystemSuperStructure indexSubsystemSuperStructure) {
		this.m_indexSubsystemSuperStructure = indexSubsystemSuperStructure;
	}

	@Override
	public void initialize() {
		startPosition = m_indexSubsystemSuperStructure.getIndexerMotorBackSubsystem().getEncoder().getPosition();
		m_indexSubsystemSuperStructure.getIndexerMotorBackSubsystem().addPID(-IndexerConstants.SHIFT_DISTANCE);
	}

	@Override
	public boolean isFinished() {
		return Math.abs(m_indexSubsystemSuperStructure.getIndexerMotorBackSubsystem().getEncoder().getPosition()
				- startPosition) > 2; // Deadband = 2 rotations
	}

}
