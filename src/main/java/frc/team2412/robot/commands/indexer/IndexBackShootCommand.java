package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

public class IndexBackShootCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private LiftMotorSubsystem m_indexerMidMotorSubsystem;

	public IndexBackShootCommand(IndexerMotorSubsystem indexerMotorSubsystem,
			LiftMotorSubsystem midMotorSubsystem) {
		m_indexerMotorSubsystem = indexerMotorSubsystem;
		m_indexerMidMotorSubsystem = midMotorSubsystem;
		addRequirements(indexerMotorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMidMotorSubsystem.up();
		m_indexerMotorSubsystem.setBackMotor(-1);
		// m_IntakeOnOffSubsystem.backIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
