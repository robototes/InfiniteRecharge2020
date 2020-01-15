package LiftComands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.LiftSubsystem;

public class LiftUp extends CommandBase{
	
	public LiftUp() {
		addRequirements(LiftSubsystem);
	}

	@Override
	public void execute() {
		// run the example method
		LiftSubsystem.liftUp();
		RobotMap.LiftIsUp = true;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
