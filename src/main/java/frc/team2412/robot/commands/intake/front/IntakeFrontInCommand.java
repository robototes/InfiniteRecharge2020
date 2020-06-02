package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeFrontInCommand extends CommandBase {

	private IntakeMotorSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontInCommand(IntakeMotorSubsystem intakeMotorOnOffSubsystem) {
		this(intakeMotorOnOffSubsystem, true);
	}

	public IntakeFrontInCommand(IntakeMotorSubsystem intakeMotorOnOffSubsystem, boolean require) {
		if (require) {
			addRequirements(intakeMotorOnOffSubsystem);
		}
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.frontIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
