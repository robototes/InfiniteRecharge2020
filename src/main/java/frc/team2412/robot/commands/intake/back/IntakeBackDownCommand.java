package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBackDownCommand extends CommandBase {

	private IntakeLiftSubsystem m_intakeUpDownSubsystem;

	public IntakeBackDownCommand(IntakeLiftSubsystem intakeUpDownSubsystem) {
		addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.backIntakeDown();
	}

	@Override
	public void end(boolean cancelled) {
		if (cancelled) {
			m_intakeUpDownSubsystem.backIntakeUp();
		}
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
