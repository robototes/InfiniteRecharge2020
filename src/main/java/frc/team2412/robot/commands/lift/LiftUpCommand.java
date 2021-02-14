package frc.team2412.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class LiftUpCommand extends CommandBase {

	private LiftSubsystem liftSubsystem;
	private IndexerSubsystemSuperStructure motorSubsystem;

	public LiftUpCommand(LiftSubsystem liftSubsystem, IndexerSubsystemSuperStructure motorSubsystem) {
		addRequirements(liftSubsystem);
		this.liftSubsystem = liftSubsystem;
		this.motorSubsystem = motorSubsystem;
	}

	@Override
	public void execute() {
		liftSubsystem.liftDown();
		motorSubsystem.getIndexerMotorLiftSubsystem().addPID(-25);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
