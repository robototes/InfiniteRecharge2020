package frc.team2412.robot.Commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.HoodSubsystem;

public class HoodWithdrawCommand extends CommandBase {
	private HoodSubsystem m_HoodSubsystem;

	public HoodWithdrawCommand(HoodSubsystem subsystem) {
		m_HoodSubsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_HoodSubsystem.servoWithdraw();
	}

//adrak sucks
	@Override
	public boolean isFinished() {
		return true;
	}
}
