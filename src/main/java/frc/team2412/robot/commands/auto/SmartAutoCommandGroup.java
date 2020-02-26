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
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class SmartAutoCommandGroup extends SequentialCommandGroup {

	public SmartAutoCommandGroup(Autonomous autonomous, DriveBaseSubsystem driveBaseSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IntakeUpDownSubsystem intakeUpDownSubsystem,
			LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem,
			IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexerMotorSubsystem,
			HoodSubsystem hoodSubsystem) {

		if (autonomous.getIfTrenchPossible()) {
			addCommands(
					
					new ParallelCommandGroup(
							autonomous.getAutoCommand(),
							new IntakeFrontDownCommand(intakeUpDownSubsystem),
							new LiftUpCommand(liftSubsystem),
							new SequentialCommandGroup(
									new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem)
							)
					), 
					new ParallelCommandGroup(
							
							new LiftDownCommand(liftSubsystem),
							autonomous.getTrenchMovementCommand(),
							new SequentialCommandGroup(
									new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem)
									)
					)
					
					);
		} else {
			addCommands();
		}

	}
}
