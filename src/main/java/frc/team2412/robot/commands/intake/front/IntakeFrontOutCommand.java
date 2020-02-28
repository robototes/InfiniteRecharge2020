package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeFrontOutCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontOutCommand(IntakeOnOffSubsystem intakeMotorOnOffSubsystem) {
		this(intakeMotorOnOffSubsystem, true);
	}

	public IntakeFrontOutCommand(IntakeOnOffSubsystem intakeMotorOnOffSubsystem, boolean require) {
		if (require) {
			addRequirements(intakeMotorOnOffSubsystem);
		}
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.frontIntakeOut();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
