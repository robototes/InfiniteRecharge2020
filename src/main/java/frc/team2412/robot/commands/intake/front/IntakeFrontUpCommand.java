package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.intake.IntakeBackPneumaticSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontPneumaticSubsystem;

public class IntakeFrontUpCommand extends CommandBase {

	private IntakeFrontPneumaticSubsystem m_intakeSubsystem;

	public IntakeFrontUpCommand(IntakeFrontPneumaticSubsystem intakeSubsystem) {
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
