package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.auto.subsistentCommands.MoveToIntakePowerCellsCommandGroup;
import frc.team2412.robot.commands.drive.MoveFromPowerCellsCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;
import frc.team2412.robot.subsystems.intake.IntakeFrontMotorSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontPneumaticSubsystem;

public class SixBallAutoCommandGroup extends SequentialCommandGroup {

	public SixBallAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, LiftSubsystem liftSubsystem,
			IntakeFrontMotorSubsystem intakeFrontMotorSubsystem, IntakeFrontPneumaticSubsystem intakeFrontPneumaticSubsystem,
			FlywheelSubsystem flywheelSubsystem, IndexerSensorSubsystem indexerSensorSubsystem,
			IndexerSubsystemSuperStructure indexerMotorSubsystem, HoodSubsystem hoodSubsystem) {

		addCommands(

				new LiftUpCommand(liftSubsystem, indexerMotorSubsystem),
				new IndexShootCommand(indexerMotorSubsystem),
				new MoveToIntakePowerCellsCommandGroup(driveBaseSubsystem, intakeFrontMotorSubsystem, intakeFrontPneumaticSubsystem),
				new MoveFromPowerCellsCommand(driveBaseSubsystem),
				new IndexShootCommand(indexerMotorSubsystem));
	}

}
