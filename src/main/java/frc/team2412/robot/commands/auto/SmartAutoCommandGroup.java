package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.flywheel.FlywheelRevUpCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.Autonomous;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class SmartAutoCommandGroup extends SequentialCommandGroup {

	public SmartAutoCommandGroup(Autonomous autonomous, DriveBaseSubsystem driveBaseSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem, IntakeLiftSubsystem intakeUpDownSubsystem,
			LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem,
			IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexerMotorSubsystem,
			IndexerMidMotorSubsystem indexMidMotorSubsystem, HoodSubsystem hoodSubsystem) {

		if (autonomous.getIfTrenchPossible()) {
			addCommands(

					new ParallelCommandGroup(
							//
							autonomous.getAutoCommand(),
							//
							new IntakeFrontDownCommand(intakeUpDownSubsystem),
							//
							new LiftUpCommand(liftSubsystem),
							//
							new SequentialCommandGroup(new FlywheelRevUpCommand(flywheelSubsystem, 10),
									new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem,
											indexMidMotorSubsystem)
							//
							)
					//
					),
					//
					new ParallelCommandGroup(
							//
							autonomous.getTrenchMovementCommand(),
							//
							new LiftDownCommand(liftSubsystem),
							//
							autonomous.getTrenchMovementCommand(),
							//
							new SequentialCommandGroup(new FlywheelRevUpCommand(flywheelSubsystem, 10),
									new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem,
											indexMidMotorSubsystem)
							//
							)
					//
					)

			);
		} else {
			addCommands(
					//
					new LiftUpCommand(liftSubsystem),
					//
					new SequentialCommandGroup(
							// add rev up flyhweel command Parker was here
							new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem, indexMidMotorSubsystem)
					//
					),
					//
					new IntakeFrontDownCommand(intakeUpDownSubsystem),
					//
					autonomous.getAutoCommand(),
					//
					new SequentialCommandGroup(
							// add rev up flyhweel command
							new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem, indexMidMotorSubsystem)
					//
					)

			);
		}

	}
}
