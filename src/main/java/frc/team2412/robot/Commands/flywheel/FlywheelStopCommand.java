package frc.team2412.robot.Commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;

public class FlywheelStopCommand extends CommandBase {
	FlywheelSubsystem m_Subsystem;

	public FlywheelStopCommand(FlywheelSubsystem subsystem) {
		m_Subsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_Subsystem.Stop();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
