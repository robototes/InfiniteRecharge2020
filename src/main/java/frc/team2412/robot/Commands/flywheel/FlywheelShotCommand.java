package frc.team2412.robot.Commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;

public class FlywheelShotCommand extends CommandBase {
	FlywheelSubsystem m_Subsystem;

	public FlywheelShotCommand(FlywheelSubsystem subsystem) {
		m_Subsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_Subsystem.Shoot();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
