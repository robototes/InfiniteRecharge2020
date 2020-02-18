package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Commands.LiftCommands.LiftUpCommand;
import frc.team2412.robot.Commands.flywheel.FlywheelShootCommand;
import frc.team2412.robot.Commands.hood.HoodExtendCommand;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

public class StartUpAutoCommand extends ParallelCommandGroup {

	public StartUpAutoCommand(FlywheelSubsystem flywheelSubsystem, HoodSubsystem hoodSubsystem,
			LiftSubsystem liftSubsystem, TurretSubsystem turretSubsystem) {

		addCommands(
				
				new FlywheelShootCommand(flywheelSubsystem), 
				new HoodExtendCommand(hoodSubsystem),
				new LiftUpCommand(liftSubsystem)
				
				);

	}

}
