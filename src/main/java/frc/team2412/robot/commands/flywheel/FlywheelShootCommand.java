package frc.team2412.robot.commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.FlywheelSubsystem;

public class FlywheelShootCommand extends CommandBase {
	private FlywheelSubsystem m_FlywheelSubsystem;

	public FlywheelShootCommand(FlywheelSubsystem subsystem) {
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
		return m_FlywheelSubsystem.getSpeed() > 0.75;
	}
}
