package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBackInCommand extends CommandBase {

	private IntakeMotorSubsystem m_intakeOnOffSubsystem;

	public IntakeBackInCommand(IntakeMotorSubsystem intakeOnOffSubsystem) {
		this(intakeOnOffSubsystem, true);
	}

	public IntakeBackInCommand(IntakeMotorSubsystem intakeOnOffSubsystem, boolean require) {
		if (require) {
			addRequirements(intakeOnOffSubsystem);
		}
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.backIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
