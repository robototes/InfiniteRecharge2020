package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.Commands.IndexerCommands.IndexShootCommand;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

public class FullSixBallAutoCommand extends SequentialCommandGroup {

	public FullSixBallAutoCommand(DriveBaseSubsystem driveBaseSubsystem, FlywheelSubsystem flywheelSubsystem,
			HoodSubsystem hoodSubsystem, IndexerMotorSubsystem indexerMotorSubsystem,
			IndexerSensorSubsystem indexerSensorSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem, TurretSubsystem turretSubsystem) {

		addCommands(

				new StartUpAutoCommand(flywheelSubsystem, hoodSubsystem, liftSubsystem, turretSubsystem),
				new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem),
				new MoveToPowerCellAutoCommand(driveBaseSubsystem, intakeOnOffSubsystem, intakeUpDownSubsystem),
				new MoveFromPowerCellAutoCommand(driveBaseSubsystem, intakeOnOffSubsystem, intakeUpDownSubsystem,
						flywheelSubsystem, hoodSubsystem, liftSubsystem, turretSubsystem),
				new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem)

		);

	}

}
