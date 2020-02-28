package frc.team2412.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class LiftUpCommand extends CommandBase {

	private LiftSubsystem liftSubsystem;
	private IndexerMotorSubsystem motorSubsystem;

	public LiftUpCommand(LiftSubsystem liftSubsystem, IndexerMotorSubsystem motorSubsystem) {
		addRequirements(liftSubsystem);
		this.liftSubsystem = liftSubsystem;
		this.motorSubsystem = motorSubsystem;
	}

	@Override
	public void execute() {
		// run the example method
		liftSubsystem.liftUp();
		
	}

	@Override
	public boolean isFinished() {
		motorSubsystem.setMidPID(true);
		return true;
	}

}
