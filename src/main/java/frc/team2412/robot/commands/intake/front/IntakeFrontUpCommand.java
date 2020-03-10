package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeFrontUpCommand extends CommandBase {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public IntakeFrontUpCommand(IntakeUpDownSubsystem intakeUpDownSubsystem) {
		this(intakeUpDownSubsystem, true);
	}

	public IntakeFrontUpCommand(IntakeUpDownSubsystem intakeUpDownSubsystem, boolean require) {
		if (require)
			addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.frontIntakeUp();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
