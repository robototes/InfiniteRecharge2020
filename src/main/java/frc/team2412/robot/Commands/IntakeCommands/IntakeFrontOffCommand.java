package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;

public class IntakeFrontOffCommand extends CommandBase {

	private IntakeMotorOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontOffCommand(IntakeMotorOnOffSubsystem intakeMotorOnOffSubsystem) {
		addRequirements(intakeMotorOnOffSubsystem);
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.frontIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
