package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeFrontDownCommand extends CommandBase {

	private IntakeLiftSubsystem m_intakeUpDownSubsystem;

	public IntakeFrontDownCommand(IntakeLiftSubsystem intakeUpDownSubsystem) {
		this(intakeUpDownSubsystem, true);
	}

	public IntakeFrontDownCommand(IntakeLiftSubsystem intakeUpDownSubsystem, boolean require) {
		if (require)
			addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.frontIntakeDown();
	}

	@Override
	public void end(boolean cancelled) {
		if (cancelled) {
			m_intakeUpDownSubsystem.frontIntakeUp();
		}
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
