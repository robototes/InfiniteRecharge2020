package frc.team2412.robot.commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.FlywheelSubsystem;

public class FlywheelRevUpCommand extends CommandBase {
	private FlywheelSubsystem m_flywheelSubsystem;
	private double m_wantedMPS;

	public FlywheelRevUpCommand(FlywheelSubsystem subsystem, double wantedMPS) {
		m_flywheelSubsystem = subsystem;
		m_wantedMPS = wantedMPS;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		m_flywheelSubsystem.setRPMFromMPS(m_wantedMPS);
	}
	
	@Override
	public boolean isFinished() {
		return Math.abs(m_flywheelSubsystem.currentLeftSpeedInMetersPerSecond()- m_wantedMPS) < 0.01;
	}
}
