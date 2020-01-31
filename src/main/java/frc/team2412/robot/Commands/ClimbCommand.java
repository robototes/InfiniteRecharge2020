package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;

public class ClimbCommand extends CommandBase {
	ClimbLiftSubsystem m_ClimbLiftSubsystem;

	public ClimbCommand(ClimbLiftSubsystem climbLiftSubsystem) {

		m_ClimbLiftSubsystem = climbLiftSubsystem;
		addRequirements(climbLiftSubsystem);
	}

	public ClimbCommand(DoubleSolenoid leftPneumatic, DoubleSolenoid rightPneumatic) {
		// TODO Auto-generated constructor stub
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
