package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Commands.DriveCommands.MoveFromPowerCellDriveCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontBothOffCommandGroup;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

public class MoveFromPowerCellAutoCommand extends ParallelCommandGroup {

	public MoveFromPowerCellAutoCommand(DriveBaseSubsystem driveBaseSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IntakeUpDownSubsystem intakeUpDownSubsystem,
			FlywheelSubsystem flywheelSubsystem, HoodSubsystem hoodSubsystem, LiftSubsystem liftSubsystem,
			TurretSubsystem turretSubsystem) {

		addCommands(new MoveFromPowerCellDriveCommand(driveBaseSubsystem),
				new IntakeFrontBothOffCommandGroup(intakeUpDownSubsystem, intakeOnOffSubsystem),
				new StartUpAutoCommand(flywheelSubsystem, hoodSubsystem, liftSubsystem, turretSubsystem));

	}

}
