package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.auto.subsistentCommands.MoveToIntakePowerCellsCommandGroup;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class ThreeBallAutoCommandGroup extends SequentialCommandGroup {

	public ThreeBallAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeMotorSubsystem intakeOnOffSubsystem,
			IntakeLiftSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem, FlywheelSubsystem flywheelSubsystem,
			IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexerMotorSubsystem,
			IndexerMidMotorSubsystem indexMidMotorSubsystem, HoodSubsystem hoodSubsystem) {

		addCommands(

				new LiftUpCommand(liftSubsystem),
				new IndexShootCommand(indexerSensorSubsystem, indexerMotorSubsystem, indexMidMotorSubsystem),
				new MoveToIntakePowerCellsCommandGroup(driveBaseSubsystem, intakeOnOffSubsystem,
						intakeUpDownSubsystem));
	}

}
