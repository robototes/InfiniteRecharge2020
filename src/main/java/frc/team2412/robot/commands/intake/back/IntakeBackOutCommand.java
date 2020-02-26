package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBackOutCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeBackOutCommand(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeOnOffSubsystem);
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.backIntakeOut();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
