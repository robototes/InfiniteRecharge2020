package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IndexFrontShootCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	private IntakeOnOffSubsystem m_IntakeOnOffSubsystem;

	public IndexFrontShootCommand(IndexerMotorSubsystem indexerMotorSubsystem, IndexerLiftMotorSubsystem indexerLiftMotorSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {

    m_indexerMotorSubsystem = indexerMotorSubsystem;
		m_IntakeOnOffSubsystem = intakeOnOffSubsystem;
		addRequirements(indexerMotorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerLiftMotorSubsystem.setMotorSpeed(1);
		m_indexerMotorSubsystem.setFrontMotor(-1);
//		m_IntakeOnOffSubsystem.frontIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
