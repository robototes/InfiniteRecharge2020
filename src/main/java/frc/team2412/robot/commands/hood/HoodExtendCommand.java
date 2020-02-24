package frc.team2412.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.HoodSubsystem;

public class HoodExtendCommand extends CommandBase {
	private HoodSubsystem m_hoodSubsystem;

	public HoodExtendCommand(HoodSubsystem subsystem) {
		m_hoodSubsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_hoodSubsystem.servoExtend();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
