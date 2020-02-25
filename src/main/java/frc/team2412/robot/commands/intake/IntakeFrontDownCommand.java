package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeFrontDownCommand extends CommandBase {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public IntakeFrontDownCommand(IntakeUpDownSubsystem intakeUpDownSubsystem) {
		addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.frontIntakeDown();
	}

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
