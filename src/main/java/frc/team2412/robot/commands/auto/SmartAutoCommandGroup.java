package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.Autonomous;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class SmartAutoCommandGroup extends SequentialCommandGroup {

	public SmartAutoCommandGroup(Autonomous autonomous, DriveBaseSubsystem driveBaseSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IntakeUpDownSubsystem intakeUpDownSubsystem,
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