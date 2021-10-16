package frc.team2412.robot.autonomous;

import static frc.team2412.robot.subsystems.constants.AutoConstants.*;
import static frc.team2412.robot.subsystems.constants.AutoConstants.config;
import static frc.team2412.robot.subsystems.constants.AutoConstants.kDriveKinematics;
import static frc.team2412.robot.subsystems.constants.AutoConstants.pidController;
import static frc.team2412.robot.subsystems.constants.AutoConstants.ramseteControlller;
import static frc.team2412.robot.subsystems.constants.AutoConstants.simpleMotorFeedforward;
import static frc.team2412.robot.subsystems.constants.AutoConstants.squarePathTrajectory;

import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.indexer.IndexLiftShootSlowCommand;
import frc.team2412.robot.commands.indexer.IndexLiftStopCommand;
import frc.team2412.robot.commands.limelight.LimelightReadCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class Autonomous {

	public Trajectory trajectory;
	private static DriveBaseSubsystem driveSub = RobotMap.m_robotContainer.m_driveBaseSubsystem;
	private static LimelightSubsystem limelightSub = RobotMap.m_robotContainer.m_limelightSubsystem;
	private static TurretSubsystem turretSub = RobotMap.m_robotContainer.m_turretSubsystem;
	private static FlywheelSubsystem flywheelSub = RobotMap.m_robotContainer.m_flywheelSubsystem;
	private static HoodSubsystem hoodSub = RobotMap.m_robotContainer.m_hoodSubsystem;
	private static IndexerSubsystemSuperStructure indexerMotorSub = RobotMap.m_robotContainer.m_indexerMotorSubsystem;
	private static IntakeMotorSubsystem intakeSub = RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem;
	private static IntakeLiftSubsystem intakePneuSub = RobotMap.m_robotContainer.m_intakeUpDownSubsystem;

	// Does this work?  Has it been tested?
	public static Command getMoveCertainAmountCommand(double finalX, double finalY) {

		Pose2d currentPose = driveSub.getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + (finalX / 2),
						currentTranslation.getY() + finalY / 2)),
				new Pose2d(currentTranslation.getX() + finalX, currentTranslation.getY() + finalY,
						currentPose.getRotation()),
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				driveSub::tankDriveVolts, driveSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> driveSub.tankDriveVolts(0, 0));

	}

	public static RamseteCommand getRamseteCommand(Trajectory trajectory) {

		// Run path following command, then stop at the end.
		return new RamseteCommand(trajectory, driveSub::getPose, ramseteControlller, simpleMotorFeedforward,
				kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				driveSub::tankDriveVolts, driveSub);
	}

	public static InstantCommand resetPositionCommand() {
		InstantCommand command = new InstantCommand(new Runnable() {
			@Override
			public void run() {
				driveSub.resetPos	();
			}
		});
		return command;
	}

	public static InstantCommand setPositionCommand(Pose2d pose) {
		InstantCommand command = new InstantCommand(new Runnable() {
			@Override
			public void run() {
				driveSub.setPose(pose);
			}
		});
		return command;
	}

	// TODO : Not the ideal way to get and set
	public static InstantCommand setShooterFromLimelightCommand() {
		InstantCommand command = new InstantCommand(new Runnable() {
			@Override
			public void run() {
				Optional<ShooterDistanceDataPoint> opPoint = limelightSub.getDistanceData();
				opPoint.ifPresent(point ->{
					//System.out.println(point);
					flywheelSub.setSpeed((point.m_shooterPower.value()) / 5500);
					hoodSub.setServo(point.m_hoodAngle.value());
				});
			}
		});
		return command;
	}

	public static Command getSquarePathCommand() {
		Trajectory adjustedTrajectory = squarePathTrajectory.relativeTo(squarePathTrajectory.getInitialPose());
		
		RamseteCommand command = new RamseteCommand(squarePathTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				driveSub::tankDriveVolts, driveSub);

		System.out.println("square path command timing:");
		System.out.println(squarePathTrajectory.getTotalTimeSeconds());
		System.out.println(squarePathTrajectory.getStates());
		// Run path following command, then stop at the end.
		return resetPositionCommand()
		.andThen(new DriveShiftToLowGearCommand(driveSub))
		.andThen(new WaitCommand(1.0)).andThen(command).andThen(() -> driveSub.tankDriveVolts(0, 0));
	}

	// Autonomous get move forward off of the line command
	public static Command getAutoForwardOffOfLineCommand() {
		Trajectory adjustedTrajectory = autoForwardOffLineTrajectory.relativeTo(autoForwardOffLineTrajectory.getInitialPose());

		RamseteCommand command = new RamseteCommand(adjustedTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				driveSub::tankDriveVolts, driveSub);
		// Makes robot think it's in position to start the trajectory i.e. resets it
		// Run path following command, then stop at the end.
		return resetPositionCommand().andThen(command).andThen(() -> driveSub.tankDriveVolts(0, 0));
	}

	public static Command getAuto3PCsAndMoveCommand() {
		// Trajectory adjustedTrajectory = autoForwardOffLineTrajectory.relativeTo(autoForwardOffLineTrajectory.getInitialPose());

		// RamseteCommand moveCommand = new RamseteCommand(adjustedTrajectory, driveSub::getPose, ramseteControlller,
		// 		simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
		// 		driveSub::tankDriveVolts, driveSub);
		Trajectory driveForwardAdjustedTrajectory = autoForwardOffLineTrajectory.relativeTo(autoForwardOffLineTrajectory.getInitialPose());

		RamseteCommand driveForwardOffLine = new RamseteCommand(driveForwardAdjustedTrajectory, driveSub::getPose, ramseteControlller,
			simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
			driveSub::tankDriveVolts, driveSub);

		Trajectory backIntoTrenchAdjustedTrajectory = backIntoTrenchTrajectory.relativeTo(backIntoTrenchTrajectory.getInitialPose());

		RamseteCommand backIntoTrench = new RamseteCommand(backIntoTrenchAdjustedTrajectory, driveSub::getPose, ramseteControlller,
			simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
			driveSub::tankDriveVolts, driveSub);
	  
		Trajectory pullOutOfTrenchAdjustedTrajectory = pullOutOfTrenchTrajectory.relativeTo(pullOutOfTrenchTrajectory.getInitialPose());

		RamseteCommand pullOutOfTrench = new RamseteCommand(pullOutOfTrenchAdjustedTrajectory, driveSub::getPose, ramseteControlller,
			simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
			driveSub::tankDriveVolts, driveSub);
		// TODO : This seems overly complicated and unoptimized due to some commands never ending.
		// TODO : Includes complete guesses on timing for shooting and turret to line up?
		// TODO : Do we have to wait longer for limelight to finish booting?
		// TODO : Would it be better to check status of sensors instead of using timings?
		// TODO : Do all these commands work?
		// TODO : We may want to play with the ordering
		return resetPositionCommand().andThen(new LimelightReadCommand(limelightSub)
			.alongWith(
				new WaitCommand(3)
				//.andThen(new TurretFollowLimelightCommand(turretSub, limelightSub)).withTimeout(15.0)
				//  .andThen(new WaitCommand(1.0))

				.andThen(new IndexLiftShootSlowCommand(indexerMotorSub))
				.andThen(new WaitCommand(3))
				.andThen(new IndexLiftStopCommand(indexerMotorSub))
				.andThen(new WaitCommand(1))

				//.andThen(new FlywheelStopCommand(flywheelSub))
				//.andThen(new HoodAdjustCommand(hoodSub, 0))

				// this is the auto for comp
				.andThen(driveForwardOffLine)

				//get your trench balls experimental auto
				// .andThen(backIntoTrench)
				// .alongWith(new IntakeBackDownCommand(intakePneuSub))
				// .alongWith(new IntakeBackInCommand(intakeSub))
				// .alongWith(new InstantCommand(() -> RobotMap.m_robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().in()))
				// .andThen(pullOutOfTrench)
				// .alongWith(new IntakeBackUpCommand(intakePneuSub))
				// .alongWith(new IntakeBackOffCommand(intakeSub))
				// .alongWith(new InstantCommand(() -> RobotMap.m_robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop()))
				// .andThen(new IndexLiftShootCommand(indexerMotorSub))
				// .andThen(new WaitCommand(5))
				// .andThen(new IndexLiftStopCommand(indexerMotorSub))
			)
		);
		/*
		return resetPositionCommand().andThen(new WaitCommand(1.0))
									 .andThen(new LimelightReadCommand(limelightSub))
									 .andThen(setShooterFromLimelightCommand())
 									 .alongWith(new TurretFollowLimelightCommand(turretSub, limelightSub)).withTimeout(15.0)
									 .andThen(new WaitCommand(1.0))
									 .andThen(new IndexLiftShootCommand(indexerMotorSub)).withTimeout(7)
									 .andThen(moveCommand)
									 .andThen(new IndexLiftStopCommand(indexerMotorSub))
									 .andThen(new FlywheelStopCommand(flywheelSub));
		*/
	 }
}