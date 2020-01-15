package frc.team2412.robot.Commands.LiftCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.LiftSubsystem;

public class LiftDown extends CommandBase{
	
	public LiftDown() {
		addRequirements(liftSubsystem);
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
