package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IndexFrontShootCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IntakeOnOffSubsystem m_IntakeOnOffSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	
	public IndexFrontShootCommand(IndexerMotorSubsystem indexerMotorSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IndexerLiftMotorSubsystem IndexerLiftMotorSubsystem) {
		m_indexerMotorSubsystem = indexerMotorSubsystem;
		m_IntakeOnOffSubsystem = intakeOnOffSubsystem;
		addRequirements(indexerMotorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerLiftMotorSubsystem.setMidMotor(1);
		m_indexerMotorSubsystem.setFrontMotor(-1);
		m_IntakeOnOffSubsystem.frontIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
