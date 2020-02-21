package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;

public class ClimbExtendArmCommand extends CommandBase {

	ClimbMotorSubsystem m_ClimbMotorSubsystem;

	public ClimbExtendArmCommand(ClimbMotorSubsystem climbMotorSubsystem) {
		m_ClimbMotorSubsystem = climbMotorSubsystem;
		addRequirements(climbMotorSubsystem);
	}

	@Override
	public void end(boolean canceled) {
		m_ClimbMotorSubsystem.climbStop();
	}

	@Override
	public void execute() {
		m_ClimbMotorSubsystem.climbExtendArm();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
