package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;

public class ClimbStopArmCommand extends CommandBase {

	ClimbMotorSubsystem m_ClimbMotorSubsystem;

	public ClimbStopArmCommand(ClimbMotorSubsystem climbMotorSubsystem) {
		m_ClimbMotorSubsystem = climbMotorSubsystem;
		addRequirements(climbMotorSubsystem);
	}

	@Override
	public void execute() {
		m_ClimbMotorSubsystem.climbStop();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
