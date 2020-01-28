package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;

public class IntakeFrontOnIntakeBackOffCommand extends CommandBase {

	private IntakeMotorOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontOnIntakeBackOffCommand(IntakeMotorOnOffSubsystem intakeMotorSubsystem) {
		addRequirements(intakeMotorSubsystem);
		this.m_intakeMotorOnOffSubsystem = intakeMotorSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.frontIntakeOnBackIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
