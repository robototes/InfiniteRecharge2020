package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;

public class IntakeFrontOnIntakeBackOffCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeFrontOnIntakeBackOffCommand(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeOnOffSubsystem);
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.frontIntakeOnBackIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
