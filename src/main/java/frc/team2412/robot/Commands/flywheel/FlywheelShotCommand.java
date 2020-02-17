package frc.team2412.robot.Commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;

public class FlywheelShotCommand extends CommandBase {
	private FlywheelSubsystem m_FlywheelSubsystem;

	public FlywheelShotCommand(FlywheelSubsystem subsystem) {
		m_FlywheelSubsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_FlywheelSubsystem.shoot();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
