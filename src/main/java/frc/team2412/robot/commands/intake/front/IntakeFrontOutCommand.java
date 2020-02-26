package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeFrontOutCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontOutCommand(IntakeOnOffSubsystem intakeMotorOnOffSubsystem) {
		addRequirements(intakeMotorOnOffSubsystem);
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
