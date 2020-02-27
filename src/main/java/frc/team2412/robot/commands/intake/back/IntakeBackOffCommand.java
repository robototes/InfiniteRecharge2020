package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBackOffCommand extends CommandBase {

	private IntakeMotorSubsystem m_intakeOnOffSubsystem;

	public IntakeBackOffCommand(IntakeMotorSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeOnOffSubsystem);
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.backIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
