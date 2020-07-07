package frc.team2412.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.flywheel.FlywheelSetSpeedCommand;
import frc.team2412.robot.commands.hood.HoodSetAngleCommand;
import frc.team2412.robot.commands.turret.TurretAddSupplierCommand;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;

public class SetShooterValueCommand extends ParallelCommandGroup {

	public SetShooterValueCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem,
			HoodSubsystem hoodSubsystem, FlywheelSubsystem flywheelSubsystem) {

		this.addCommands(
				new ConditionalCommand(new TurretAddSupplierCommand(turretSubsystem, limelightSubsystem::getSkewData),
						new InstantCommand(), limelightSubsystem::innerGoalPossible),
				new HoodSetAngleCommand(hoodSubsystem, limelightSubsystem::getDistanceData),
				new FlywheelSetSpeedCommand(flywheelSubsystem, limelightSubsystem::getDistanceData));
	}

}
