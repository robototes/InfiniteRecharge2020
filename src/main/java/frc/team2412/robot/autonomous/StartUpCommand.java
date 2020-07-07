package frc.team2412.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.flywheel.FlywheelShootCommand;
import frc.team2412.robot.commands.hood.HoodExtendCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class StartUpCommand extends ParallelCommandGroup {

	public StartUpCommand(LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem, HoodSubsystem hoodSubsystem,
			IndexerSubsystemSuperStructure motorSubsystem) {

		addCommands(

				new LiftUpCommand(liftSubsystem, motorSubsystem), new FlywheelShootCommand(flywheelSubsystem),
				new HoodExtendCommand(hoodSubsystem)

		);
	}

}
