package frc.team2412.robot.autonomous;

import static frc.team2412.robot.subsystems.constants.AutoConstants.bouncePathTrajectory;
import static frc.team2412.robot.subsystems.constants.AutoConstants.config;
import static frc.team2412.robot.subsystems.constants.AutoConstants.kDriveKinematics;
import static frc.team2412.robot.subsystems.constants.AutoConstants.pidController;
import static frc.team2412.robot.subsystems.constants.AutoConstants.ramseteControlller;
import static frc.team2412.robot.subsystems.constants.AutoConstants.simpleMotorFeedforward;
import static frc.team2412.robot.subsystems.constants.AutoConstants.squarePathTrajectory;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class Autonomous {

	public Trajectory trajectory;
	private static DriveBaseSubsystem driveSub = RobotMap.m_robotContainer.m_driveBaseSubsystem;

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
				driveSub.resetPos();
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

	public static Command getSquarePathCommand() {
		Trajectory adjustedTrajectory = squarePathTrajectory.relativeTo(squarePathTrajectory.getInitialPose());
		
		RamseteCommand command = new RamseteCommand(squarePathTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				driveSub::tankDriveVolts, driveSub);

		System.out.println("square path command timing:");
		System.out.println(squarePathTrajectory.getTotalTimeSeconds());
		System.out.println(squarePathTrajectory.getStates());
		// Run path following command, then stop at the end.
		return resetPositionCommand().andThen(new WaitCommand(1.0)).andThen(command).andThen(() -> driveSub.tankDriveVolts(0, 0));
	}

	public static Command getBouncePathCommand() {
		Trajectory adjustedTrajectory = bouncePathTrajectory.relativeTo(bouncePathTrajectory.getInitialPose());

		RamseteCommand command = new RamseteCommand(adjustedTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				driveSub::tankDriveVolts, driveSub);
		// Makes robot think it's in position to start the trajectory i.e. resets it
		// Run path following command, then stop at the end.
		return resetPositionCommand().andThen(new WaitCommand(1.0)).andThen(command).andThen(() -> driveSub.tankDriveVolts(0, 0));
	}
}