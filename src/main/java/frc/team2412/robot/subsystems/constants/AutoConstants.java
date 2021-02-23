package frc.team2412.robot.subsystems.constants;

import java.util.List;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class AutoConstants {

	public static final double MAX_VOLTAGE = 12;

	public static final boolean kGyroReversed = false;

	public static final double ksVolts = 0.185;

	public static final double kvVoltSecondsPerMeter = 0.0754;

	public static final double kaVoltSecondsSquaredPerMeter = 0.0105;

	public static final double kPDriveVel = 0.488;

	public static final double kTrackwidthMeters = 0.5461; // Horizontal distance between wheels

	public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(

			kTrackwidthMeters);

	public static final double kMaxSpeedMetersPerSecond = 3; // Max speed we can drive

	public static final double kMaxAccelerationMetersPerSecondSquared = 3;

	// Ramsete Controller Value

	public static final double kRamseteB = 2; // makes a more straight curve

	public static final double kRamseteZeta = 0.7; // limits the correction

	public static final double controlPanelCutOff = 4.0;

	public static final double inititationLineMeters = 3.048;

	// Create a voltage constraint to ensure we don't accelerate too fast

	public static DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(

			new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), kDriveKinematics,

			10);

	// Create config for trajectory

	public static TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond,

			kMaxAccelerationMetersPerSecondSquared)

					// Add kinematics to ensure max speed is actually obeyed

					.setKinematics(kDriveKinematics)

					// Apply the voltage constraint

					.addConstraint(autoVoltageConstraint);

	public static SimpleMotorFeedforward simpleMotorFeedforward = new SimpleMotorFeedforward(ksVolts,

			kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

	public static RamseteController ramseteControlller = new RamseteController(kRamseteB, kRamseteZeta);

	public static PIDController pidController = new PIDController(kPDriveVel, 0, 0);

	public static DriveBaseSubsystem driveSub = RobotMap.m_robotContainer.m_driveBaseSubsystem;

	public static Translation2d slalomPointOne = new Translation2d(0.868, -4.273);
	public static Translation2d slalomPointTwo = new Translation2d(2.233, -4.063);
	public static Translation2d slalomPointThree = new Translation2d(3.02, -2.898);
	public static Translation2d slalomPointFour = new Translation2d(4.844, -1.962);
	public static Translation2d slalomPointFive = new Translation2d(6.839, -2.564);
	public static Translation2d slalomPointSix = new Translation2d(7.379, -3.364);
	public static Translation2d slalomPointSeven = new Translation2d(8.429, -4.289);
	public static Translation2d slalomPointEight = new Translation2d(9.585, -3.247);
	public static Translation2d slalomPointNine = new Translation2d(8.375, -2.213);
	public static Translation2d slalomPointTen = new Translation2d(7.299, -3.308);
	public static Translation2d slalomPointEleven = new Translation2d(6.758, -4.12);
	public static Translation2d slalomPointTwelve = new Translation2d(3.937, -4.303);
	public static Translation2d slalomPointThirteen = new Translation2d(2.05, -2.767);
	public static Translation2d slalomPointFourteen = new Translation2d(0.664, -2.348);

	public static List<Translation2d> interiorWaypointsSlalomPath = List.of(slalomPointTwo, slalomPointThree,
			slalomPointFour, slalomPointFive, slalomPointSix, slalomPointSeven, slalomPointEight, slalomPointNine,
			slalomPointTen, slalomPointEleven, slalomPointTwelve, slalomPointThirteen);

	public final Trajectory slalomPathTrajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(slalomPointOne, new Rotation2d(0)), interiorWaypointsSlalomPath,
			new Pose2d(slalomPointFourteen, new Rotation2d(0)), config);

}