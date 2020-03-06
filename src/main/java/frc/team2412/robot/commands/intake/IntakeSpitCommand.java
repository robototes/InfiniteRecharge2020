package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeSpitCommand extends CommandBase {

	private IntakeMotorSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeSpitCommand(IntakeMotorSubsystem intakeMotorOnOffSubsystem) {
		addRequirements(intakeMotorOnOffSubsystem);
		this.m_intakeMotorOnOffSubsystem = intakeMotorOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeMotorOnOffSubsystem.setIntake(-1);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
