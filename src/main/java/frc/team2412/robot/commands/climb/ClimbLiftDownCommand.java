package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbLiftSubsystem;

public class ClimbLiftDownCommand extends CommandBase {

	ClimbLiftSubsystem m_ClimbLiftSubsystem;

	public ClimbLiftDownCommand(ClimbLiftSubsystem climbLiftSubsystem) {
		m_ClimbLiftSubsystem = climbLiftSubsystem;
		addRequirements(climbLiftSubsystem);
	}

	@Override
	public void execute() {
		m_ClimbLiftSubsystem.retractRails();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
