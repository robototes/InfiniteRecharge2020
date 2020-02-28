package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeFrontOffIntakeBackOnCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeFrontOffIntakeBackOnCommand(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		//addRequirements(intakeOnOffSubsystem);
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.frontIntakeOffBackIntakeIn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
