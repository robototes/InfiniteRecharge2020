package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.intake.IntakeBackMotorSubsystem;

public class IntakeBackOutCommand extends CommandBase {

	private IntakeBackMotorSubsystem m_intakeSubsystem;

	public IntakeBackOutCommand(IntakeBackMotorSubsystem intakeSubsystem) {
		m_intakeSubsystem = intakeSubsystem;
		addRequirements(intakeSubsystem);
		
	}

	@Override
	public void execute() {
		m_intakeSubsystem.out();
	}
	@Override
	public boolean isFinished() {
		return true;
	}

}
