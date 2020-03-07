package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.auto.subsistentCommands.StartUpCommand;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class AAHEliMemeAutoCommandGroup extends SequentialCommandGroup {

	public AAHEliMemeAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flywheelSubsystem, IndexerSensorSubsystem indexerSensorSubsystem,
			IndexerSubsystemSuperStructure indexerMotorSubsystem, HoodSubsystem hoodSubsystem) {

		addCommands(

				new StartUpCommand(liftSubsystem, flywheelSubsystem, hoodSubsystem, indexerMotorSubsystem),
				new IndexShootCommand(indexerMotorSubsystem, intakeUpDownSubsystem),
				new MoveToPowerCellsCommand(driveBaseSubsystem)

		);
	}

}
