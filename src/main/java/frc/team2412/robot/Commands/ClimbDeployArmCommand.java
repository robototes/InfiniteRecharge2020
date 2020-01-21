package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import infiniteRecharge2020.ClimbLiftSubsystem;

public class ClimbDeployArmCommand extends CommandBase {
	
	private ClimbLiftSubsystem climbPneumatics;
	
	public ClimbDeployArmCommand(ClimbLiftSubsystem m_ClimbLiftSubsystem) {
		climbPneumatics = m_ClimbLiftSubsystem;
		addRequirements(climbPneumatics);
	}	

	public void execute() {
		climbPneumatics.DeployArm();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
