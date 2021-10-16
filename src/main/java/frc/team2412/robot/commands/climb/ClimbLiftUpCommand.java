package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class ClimbLiftUpCommand extends ParallelCommandGroup {

	public ClimbLiftUpCommand(ClimbLiftSubsystem climbLiftSubsystem, LiftSubsystem liftSubsystem,
			IndexerSubsystemSuperStructure indexerSubsystem) {
		addCommands(new LiftDownCommand(liftSubsystem),
				new InstantCommand(() -> climbLiftSubsystem.deployRails()));
	}

}
