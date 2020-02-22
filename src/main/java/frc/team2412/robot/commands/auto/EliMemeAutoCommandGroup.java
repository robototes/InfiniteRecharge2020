package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.auto.subsistentCommands.StartUpCommand;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class EliMemeAutoCommandGroup extends SequentialCommandGroup {

	public EliMemeAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flywheelSubsystem, IndexerSensorSubsystem indexerSensorSubsystem,
			IndexerMotorSubsystem indexerMotorSubsystem, HoodSubsystem hoodSubsystem) {

		addCommands(

				new StartUpCommand(liftSubsystem, flywheelSubsystem, hoodSubsystem),
				new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem),
				new MoveToPowerCellsCommand(driveBaseSubsystem)

		);
	}

}
