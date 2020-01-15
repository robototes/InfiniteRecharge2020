package LiftComands;

import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.LiftSubsystem;

public class LiftDown {
	
	public LiftDown() {
		addRequirements(LiftSubsystem);
	}

	@Override
	public void execute() {
		// run the example method
		LiftSubsystem.liftDown();
		RobotMap.LiftIsUp = false;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
