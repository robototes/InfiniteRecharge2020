package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootBackCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

	public IndexShootBackCommand(IndexerSubsystemSuperStructure indexMotorSubsystem) {
		this.m_IndexerSubsystemSuperStructure = indexMotorSubsystem;
	}

	@Override
	public void initialize() {
		m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().in();
	}

	@Override
	public void end(boolean cancel) {
		m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().stop();
	}

	@Override
	public boolean isFinished() {
		return m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().allBackSensorsOff();
	}

}
