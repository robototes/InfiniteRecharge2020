package frc.team2412.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class LiftDownCommand extends CommandBase {

	private LiftSubsystem liftSubsystem;
	private IndexerLiftMotorSubsystem motorSubsystem;

	public LiftDownCommand(LiftSubsystem liftSubsystem, IndexerLiftMotorSubsystem motorSubsystem) {
		addRequirements(liftSubsystem);
		this.liftSubsystem = liftSubsystem;
		this.motorSubsystem = motorSubsystem;
	}

	@Override
	public void execute() {
		// run the example method
		liftSubsystem.liftDown();
		motorSubsystem.setMidPID(false);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
