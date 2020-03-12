package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.intake.IntakeBackPneumaticSubsystem;

public class IntakeBackUpCommand extends CommandBase {

	private IntakeBackPneumaticSubsystem m_intakeSubsystem;

	public IntakeBackUpCommand(IntakeBackPneumaticSubsystem intakeSubsystem) {
		m_intakeSubsystem = intakeSubsystem;
		addRequirements(intakeSubsystem);
		
	}

	@Override
	public void execute() {
		m_intakeSubsystem.in();
	}

	@Override
	public void end(boolean cancelled) {
		if (cancelled) {
			m_intakeSubsystem.out();
		}
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
