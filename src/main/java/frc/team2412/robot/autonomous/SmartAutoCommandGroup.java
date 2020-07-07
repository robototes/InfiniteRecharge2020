package frc.team2412.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class SmartAutoCommandGroup extends SequentialCommandGroup {

	public SmartAutoCommandGroup(Autonomous autonomous, DriveBaseSubsystem driveBaseSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem, IntakeLiftSubsystem intakeUpDownSubsystem,
			LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem,
			IndexerSensorSubsystem indexerSensorSubsystem, IndexerSubsystemSuperStructure indexerSuperSubsystem,
			HoodSubsystem hoodSubsystem) {

		if (autonomous.getIfTrenchPossible()) {
			addCommands(new ParallelCommandGroup(
					//
					autonomous.getAutoCommand(),
					//
					new IntakeFrontDownCommand(intakeUpDownSubsystem),
					//
					new LiftUpCommand(liftSubsystem, indexerSuperSubsystem),
					//
					new IndexShootCommand(indexerSuperSubsystem)
			//
			),
					//
					new ParallelCommandGroup(
							//
							autonomous.getTrenchMovementCommand(),
							//
							new LiftDownCommand(liftSubsystem, indexerSuperSubsystem),
							//
							autonomous.getTrenchMovementCommand(),
							//
							new IndexShootCommand(indexerSuperSubsystem)
					//
					)

			);
		} else {
			addCommands(
					//
					new LiftUpCommand(liftSubsystem, indexerSuperSubsystem),
					//
					new IndexShootCommand(indexerSuperSubsystem),
					//
					new IntakeFrontDownCommand(intakeUpDownSubsystem),
					//
					autonomous.getAutoCommand(),
					//
					new IndexShootCommand(indexerSuperSubsystem)

			);
		}

	}
}