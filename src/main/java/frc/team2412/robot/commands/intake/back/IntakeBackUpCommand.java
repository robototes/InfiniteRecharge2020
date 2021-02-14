package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBackUpCommand extends CommandBase {

	private IntakeLiftSubsystem m_intakeUpDownSubsystem;

	public IntakeBackUpCommand(IntakeLiftSubsystem intakeUpDownSubsystem) {
		this(intakeUpDownSubsystem, true);
	}

	public IntakeBackUpCommand(IntakeLiftSubsystem intakeUpDownSubsystem, boolean require) {
		if (require)
			addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.backIntakeUp();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
