package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexSpitCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;
	private IntakeMotorSubsystem m_intakeOnOffSubsystem;

	public IndexSpitCommand(IndexerSubsystemSuperStructure motorSubsystem, IntakeMotorSubsystem intakeOnOffSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;

		addRequirements(motorSubsystem, intakeOnOffSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().out();
		m_intakeOnOffSubsystem.setIntake(-1);
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().stop();
		m_intakeOnOffSubsystem.intakeOff();
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
