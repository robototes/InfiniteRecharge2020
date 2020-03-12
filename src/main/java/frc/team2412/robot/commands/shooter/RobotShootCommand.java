package frc.team2412.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class RobotShootCommand extends ParallelCommandGroup {

	public RobotShootCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem,
			HoodSubsystem hoodSubsystem, FlywheelSubsystem flywheelSubsystem,
			IndexerSubsystemSuperStructure indexerSubsystemSuperStructure, LiftSubsystem liftSubsystem) {

		this.addCommands(
				new SetShooterValueCommand(limelightSubsystem, turretSubsystem, hoodSubsystem, flywheelSubsystem),
				new IndexShootCommand(indexerSubsystemSuperStructure),
				new InstantCommand(() -> indexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().out()));

	}

}
