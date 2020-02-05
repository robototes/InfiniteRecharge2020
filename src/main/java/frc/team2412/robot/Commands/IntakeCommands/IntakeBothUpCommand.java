package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;

public class IntakeBothUpCommand extends ParallelCommandGroup {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public IntakeBothUpCommand(IntakeUpDownSubsystem intakeUpDownSubsystem) {
		addRequirements(intakeUpDownSubsystem);
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
	}

	public void execute() {
		m_intakeUpDownSubsystem.frontIntakeUp();
		m_intakeUpDownSubsystem.backIntakeUp();
	}

	public boolean isFinished() {
		return true;
	}
}
