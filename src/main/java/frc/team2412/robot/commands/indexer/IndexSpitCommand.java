package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;
import frc.team2412.robot.subsystems.intake.IntakeBackMotorSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontMotorSubsystem;

public class IndexSpitCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;
	private IntakeFrontMotorSubsystem m_intakeFrontSubsystem;
	private IntakeBackMotorSubsystem m_intakeBackSubsystem;

	public IndexSpitCommand(IndexerSubsystemSuperStructure motorSubsystem, IntakeFrontMotorSubsystem front, IntakeBackMotorSubsystem back) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeFrontSubsystem = front;
		m_intakeBackSubsystem = back;

		addRequirements(motorSubsystem, front, back);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().out();
		m_intakeFrontSubsystem.out();
		m_intakeBackSubsystem.out();
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().stop();
		m_intakeFrontSubsystem.stop();
		m_intakeBackSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
