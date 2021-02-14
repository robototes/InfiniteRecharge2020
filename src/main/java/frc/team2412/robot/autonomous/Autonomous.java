package frc.team2412.robot.autonomous;

import static frc.team2412.robot.subsystems.constants.AutoConstants.config;
import static frc.team2412.robot.subsystems.constants.AutoConstants.controlPanelCutOff;
import static frc.team2412.robot.subsystems.constants.AutoConstants.driveSub;
import static frc.team2412.robot.subsystems.constants.AutoConstants.inititationLineMeters;
import static frc.team2412.robot.subsystems.constants.AutoConstants.kDriveKinematics;
import static frc.team2412.robot.subsystems.constants.AutoConstants.pidController;
import static frc.team2412.robot.subsystems.constants.AutoConstants.ramseteControlller;
import static frc.team2412.robot.subsystems.constants.AutoConstants.simpleMotorFeedforward;

import java.util.List;

import com.robototes.units.Distance;
import com.robototes.units.UnitTypes.DistanceUnits;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.team2412.robot.RobotMap;

public class Autonomous {

	public Trajectory trajectory;

	private boolean trenchPossible = true;

	public Autonomous() {
		getTrajectoryPossible();
	}

	public boolean getIfTrenchPossible() {
		return trenchPossible;
	}

	public Command getAutoCommand() {
		return getRamseteCommand(trajectory);
	}

	public Command getTrenchMovementCommand() {
		return getRamseteCommand(getTrenchTrajectory());
	}

	public Trajectory getTrenchTrajectory() {
		return TrajectoryGenerator.generateTrajectory(driveSub.getPose(), List.of(new Translation2d(8.076, -0.70485)),
				new Pose2d(9.646, 0.704, driveSub.getPose().getRotation()), config);
	}

	public Command getMoveCertainAmountCommand(double finalX, double finalY) {

		Pose2d currentPose = driveSub.getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + (finalX / 2), finalY / 2)),
				new Pose2d(currentTranslation.getX() + finalX, finalY, currentPose.getRotation()), config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, driveSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				driveSub::tankDriveVolts, driveSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> driveSub.tankDriveVolts(0, 0));

	}

	// Crazy Auto idea
	public void getTrajectoryPossible() {

		Distance hypoteneuseinInch = new Distance(
				RobotMap.m_robotContainer.m_limelightSubsystem.distanceData.m_distance.value());

		Distance hypotenuseinMeters = new Distance(hypoteneuseinInch.distance, DistanceUnits.METER);

		double robotY = Math.pow(hypotenuseinMeters.distance, 2) - Math.pow(inititationLineMeters, 2);

		Pose2d currentPose = new Pose2d(new Translation2d(inititationLineMeters, -robotY),
				Rotation2d.fromDegrees(RobotMap.m_robotContainer.m_driveBaseSubsystem.getGyroHeading()));

		trajectory = TrajectoryGenerator.generateTrajectory(currentPose, List.of(new Translation2d(5.411, -0.70485)),
				new Pose2d(7.231, 0.70485, currentPose.getRotation()), config);

		if (trajectory.getTotalTimeSeconds() < controlPanelCutOff) {
			trenchPossible = false;
			trajectory = TrajectoryGenerator.generateTrajectory(currentPose,
					List.of(new Translation2d(6, -4), new Translation2d(6.25, -3)),
					new Pose2d(5.5, -2.4, Rotation2d.fromDegrees(180)), config);
		}

	}

	public RamseteCommand getRamseteCommand(Trajectory trajectory) {

		// Run path following command, then stop at the end.
		return new RamseteCommand(trajectory, driveSub::getPose, ramseteControlller, simpleMotorFeedforward,
				kDriveKinematics, driveSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				driveSub::tankDriveVolts, driveSub);
	}
}