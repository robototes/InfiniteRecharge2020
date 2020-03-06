package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem indexerSensorSubsystem,
			IndexerSubsystemSuperStructure indexMotorSubsystem, IntakeOnOffSubsystem intakeSubsystem) {
		m_indexerMotorSubsystem = indexMotorSubsystem;
		m_intakeOnOffSubsystem = intakeSubsystem;

	}

	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public void end(boolean cancel) {
		m_intakeOnOffSubsystem.setIntake(0);
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().stop();
	}
}
