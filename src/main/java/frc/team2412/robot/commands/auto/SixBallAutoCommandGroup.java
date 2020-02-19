package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;

public class SixBallAutoCommandGroup extends SequentialCommandGroup {

	public SixBallAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flywheelSubsystem, IndexerSensorSubsystem sensorSubsystem,
			IndexerMotorSubsystem motorSubsystem, HoodSubsystem hoodSubsystem) {
		
		addCommands(
				
				new StartUpCommand(liftSubsystem, flywheelSubsystem, hoodSubsystem),
				new MoveToIntakePowerCellsCommand(driveBaseSubsystem, intakeOnOffSubsystem, intakeUpDownSubsystem)
				);
	}

}
