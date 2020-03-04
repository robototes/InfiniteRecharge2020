package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IndexSpitCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexSpitCommand(IndexerMotorSubsystem motorSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;

		addRequirements(motorSubsystem, intakeOnOffSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(1);
		m_indexerMotorSubsystem.setBackMotor(1);
		m_indexerMotorSubsystem.setMidMotor(-0.1);
		m_intakeOnOffSubsystem.setIntake(-1);
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.stopAllMotors();
		m_intakeOnOffSubsystem.intakeOff();
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
