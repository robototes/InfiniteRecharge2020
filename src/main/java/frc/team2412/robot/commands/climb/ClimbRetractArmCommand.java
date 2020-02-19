package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;

public class ClimbRetractArmCommand extends CommandBase {

	ClimbMotorSubsystem m_ClimbMotorSubsystem;

	public ClimbRetractArmCommand(ClimbMotorSubsystem climbMotorSubsystem) {
		m_ClimbMotorSubsystem = climbMotorSubsystem;
		addRequirements(climbMotorSubsystem);
	}

	@Override
	public void end(boolean canceled) {
		m_ClimbMotorSubsystem.climbStop();
	}

	@Override
	public void execute() {
		m_ClimbMotorSubsystem.climbRetractArm();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
