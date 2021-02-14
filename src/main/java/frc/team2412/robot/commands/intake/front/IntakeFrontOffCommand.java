package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeFrontOffCommand extends CommandBase {

	private IntakeMotorSubsystem m_intakeOnOffSubsystem;

	public IntakeFrontOffCommand(IntakeMotorSubsystem intakeOnOffSubsystem) {
		this(intakeOnOffSubsystem, true);
	}

	public IntakeFrontOffCommand(IntakeMotorSubsystem intakeOnOffSubsystem, boolean require) {
		if (require) {
			addRequirements(intakeOnOffSubsystem);
		}
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.frontIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
