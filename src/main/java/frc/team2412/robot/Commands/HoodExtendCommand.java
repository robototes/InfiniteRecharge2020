package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.HoodSubsystem;

public class HoodExtendCommand extends CommandBase {
	HoodSubsystem m_Subsystem;

	public HoodExtendCommand(HoodSubsystem subsystem) {
		m_Subsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		m_Subsystem.servoExtend();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
