package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;

public class IntakeBackOffCommand extends CommandBase {

	private IntakeMotorOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeBackOffCommand(IntakeMotorOnOffSubsystem intakeMotorOnOffSubsystem) {
		addRequirements(intakeMotorOnOffSubsystem);
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.backIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
