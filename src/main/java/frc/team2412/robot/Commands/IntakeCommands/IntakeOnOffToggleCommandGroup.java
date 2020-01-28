package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;

public class IntakeOnOffToggleCommandGroup extends ParallelCommandGroup {

	private IntakeMotorOnOffSubsystem m_onMotorSubsystem;
	private IntakeMotorOnOffSubsystem m_offMotorSubsystem;

	public IntakeOnOffToggleCommandGroup(IntakeMotorOnOffSubsystem onMotorSubsystem,
			IntakeMotorOnOffSubsystem offMotorSubsystem) {
		addRequirements(onMotorSubsystem, offMotorSubsystem);
		this.m_onMotorSubsystem = onMotorSubsystem;
		this.m_offMotorSubsystem = offMotorSubsystem;
	}

	@Override
	public void execute() {
		m_onMotorSubsystem.intakeOn();
		m_offMotorSubsystem.intakeOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
