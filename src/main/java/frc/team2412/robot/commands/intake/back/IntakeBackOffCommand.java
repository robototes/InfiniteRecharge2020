package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBackOffCommand extends CommandBase {

	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeBackOffCommand(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		this(intakeOnOffSubsystem, true);
	}

	public IntakeBackOffCommand(IntakeOnOffSubsystem intakeOnOffSubsystem, boolean require) {
		if (require) {
			addRequirements(intakeOnOffSubsystem);
		}
		this.m_intakeOnOffSubsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		m_intakeOnOffSubsystem.backIntakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
