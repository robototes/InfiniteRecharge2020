package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IndexBackShootCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	private IntakeOnOffSubsystem m_IntakeOnOffSubsystem;

  public IndexBackShootCommand(IndexerMotorSubsystem indexerMotorSubsystem, IndexerLiftMotorSubsystem indexerLiftMotorSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {

    m_indexerMotorSubsystem = indexerMotorSubsystem;
		m_IntakeOnOffSubsystem = intakeOnOffSubsystem;
		m_indexerLiftMotorSubsystem = indexerLiftMotorSubsystem;
		addRequirements(indexerMotorSubsystem, indexerLiftMotorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerLiftMotorSubsystem.setMotorSpeed(1);
		m_indexerMotorSubsystem.setBackMotor(-1);
	//	m_IntakeOnOffSubsystem.backIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
