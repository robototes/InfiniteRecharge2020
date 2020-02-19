package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeBackUpCommand extends CommandBase {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public IntakeBackUpCommand(IntakeUpDownSubsystem intakeUpDownSubsystem) {
		addRequirements(intakeUpDownSubsystem);
		this.m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	@Override
	public void execute() {
		m_intakeUpDownSubsystem.backIntakeUp();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
