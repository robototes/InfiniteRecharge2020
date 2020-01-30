package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

public class ClimbCommand extends CommandBase {
	ClimbLiftSubsystem m_ClimbLiftSubsystem;
	ClimbMotorSubsystem m_ClimbMotor;

	public ClimbCommand(ClimbLiftSubsystem climbLiftSubsystem) {
		
		m_ClimbLiftSubsystem = climbLiftSubsystem;
		addRequirements(climbLiftSubsystem);
	}

	private void addRequirements(ClimbLiftSubsystem climbLiftSubsystem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// run the example method
		m_ClimbLiftSubsystem.DeployRails();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
