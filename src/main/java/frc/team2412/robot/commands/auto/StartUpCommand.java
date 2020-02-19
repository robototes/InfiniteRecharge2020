package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.flywheel.FlywheelShootCommand;
import frc.team2412.robot.commands.hood.HoodExtendCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class StartUpCommand extends ParallelCommandGroup {

	public StartUpCommand(LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem,
			HoodSubsystem hoodSubsystem) {

		addCommands(
				
				new LiftUpCommand(liftSubsystem),
				new FlywheelShootCommand(flywheelSubsystem),
				new HoodExtendCommand(hoodSubsystem)
				
				);
	}

}
