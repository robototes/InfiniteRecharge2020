package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.Commands.IndexerCommands.ShootBalls;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

public class FullSixBallAutoCommand extends SequentialCommandGroup {

	public FullSixBallAutoCommand(DriveBaseSubsystem driveBaseSubsystem, FlywheelSubsystem flywheelSubsystem,
			HoodSubsystem hoodSubsystem, IndexerSubsystem indexerSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem, TurretSubsystem turretSubsystem) {

		addCommands(

				new StartUpAutoCommand(flywheelSubsystem, hoodSubsystem, liftSubsystem, turretSubsystem),
				new ShootBalls(indexerSubsystem),
				new MoveToPowerCellAutoCommand(driveBaseSubsystem, intakeOnOffSubsystem, intakeUpDownSubsystem),
				new MoveFromPowerCellAutoCommand(driveBaseSubsystem, intakeOnOffSubsystem, intakeUpDownSubsystem,
						flywheelSubsystem, hoodSubsystem, liftSubsystem, turretSubsystem),
				new ShootBalls(indexerSubsystem)
				
				);

	}

}
