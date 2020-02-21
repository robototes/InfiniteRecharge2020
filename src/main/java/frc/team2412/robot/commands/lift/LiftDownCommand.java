package frc.team2412.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class LiftDownCommand extends CommandBase {

	private LiftSubsystem liftSubsystem;

	public LiftDownCommand(LiftSubsystem liftSubsystem) {
		addRequirements(liftSubsystem);
		this.liftSubsystem = liftSubsystem;
	}

	@Override
	public void execute() {
		// run the example method
		liftSubsystem.liftDown();

	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
