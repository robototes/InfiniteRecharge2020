package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;

public class IntakeFrontOnCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeFrontOnCommand(IntakeOnOffSubsystem intakeMotorOnOffSubsystem) {
		addRequirements(intakeMotorOnOffSubsystem);
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.frontIntakeOn();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
