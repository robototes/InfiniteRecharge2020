package frc.team2412.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class LiftUpCommand extends CommandBase {

	private LiftSubsystem liftSubsystem;

	public LiftUpCommand(LiftSubsystem liftSubsystem) {
		addRequirements(liftSubsystem);
		this.liftSubsystem = liftSubsystem;
	}

	@Override
	public void execute() {
		liftSubsystem.liftUp();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
