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
import edu.wpi.first.wpilibj.util.Units;

public class AutoConstants {

	public static final double MAX_VOLTAGE = 12;

	public static final boolean kGyroReversed = true;

	// Old characterization constants
	// public static final double ksVolts = 0.185;
	// public static final double kvVoltSecondsPerMeter = 0.0754;
	// public static final double kaVoltSecondsSquaredPerMeter = 0.0105;

	// public static final double kPDriveVel = 0.488;

	// public static final double kTrackwidthMeters = Units.inchesToMeters(21.5); // Horizontal distance between wheels

	// Garage-measured characterization constants
	// public static final double /*kS*/ ksVolts = 0.6;
	// public static final double /*kV*/ kvVoltSecondsPerMeter = 3.97;
	// public static final double /*kA*/ kaVoltSecondsSquaredPerMeter = 0.266;

	// public static final double kPDriveVel = 1.69;
	// public static final double kTrackwidthMeters = 0.6419121763651794; // Horizontal distance between wheels

	// ESCWest-measured characterization constants
	public static final double /*kS*/ ksVolts = 0.578;
	public static final double /*kV*/ kvVoltSecondsPerMeter = 3.99;
	public static final double /*kA*/ kaVoltSecondsSquaredPerMeter = 0.309;

	public static final double kPDriveVel = 1.74;
	public static final double kTrackwidthMeters = 0.7833007425076656; // Horizontal distance between wheels

	public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
			kTrackwidthMeters);

	public static final double kMaxSpeedMetersPerSecond = 2; // Max speed we can drive

	public static final double kMaxAccelerationMetersPerSecondSquared = 1;


	// old constants
	// public static final double KvLinear = 1.65;
	// public static final double KaLinear = 0.18;
	// public static final double KvAngular = 1.4;
	// public static final double KaAngular = 0.2;

	// Garage measured constants (for linear - made up for angular)
	public static final double KvLinear = 3.97;
	public static final double KaLinear = 0.266;
	public static final double KvAngular = 3.7;
	public static final double KaAngular = 0.26;

	// Ramsete Controller Value

	public static final double kRamseteB = 2; //8; // makes a more straight curve
	public static final double kRamseteZeta = 0.7; //2.0; // limits the correction

	public static SimpleMotorFeedforward simpleMotorFeedforward = new SimpleMotorFeedforward(ksVolts,
			kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

	// Create a voltage constraint to ensure we don't accelerate too fast
	public static DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
			simpleMotorFeedforward, kDriveKinematics, 10);

	// Create config for trajectory
	public static TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond,
			kMaxAccelerationMetersPerSecondSquared)
					// Add kinematics to ensure max speed is actually obeyed
					.setKinematics(kDriveKinematics)
					// Apply the voltage constraint
					.addConstraint(autoVoltageConstraint);

	public static RamseteController ramseteControlller = new RamseteController(kRamseteB, kRamseteZeta);
	public static PIDController pidController = new PIDController(kPDriveVel, 0, 0);

	// Barrel Route points - Hits cones
  	//   Left in if the new Barrel is not as good as expected.
	// public static final Translation2d barrelStart = new Translation2d(1.37, -2.41);
	// public static final Translation2d barrelOne = new Translation2d(5.27, -2.4);
	// public static final Translation2d barrelTwo = new Translation2d(4.5, -4.5);
	// public static final Translation2d barrelThree  = new Translation2d(3.26, -3.07);
	// public static final Translation2d barrelFour = new Translation2d(6.52, -1.75);
	// public static final Translation2d barrelFive = new Translation2d(5.71, -1.24);
	// public static final Translation2d barrelSix = new Translation2d(7, -3.53);
	// public static final Translation2d barrelSeven = new Translation2d(8, -3);
	// public static final Translation2d barrelEight = new Translation2d(7.74, -2);
	// public static final Translation2d barrelEnd = new Translation2d(1.37, -2.41);

  	// public static List<Translation2d> interiorWaypointsBarrelPath = List.of(barrelOne, barrelTwo, barrelThree,
	// 		barrelFour, barrelFive, barrelSix, barrelSeven);

	// Barrel 3 Route points from Pathweaver
  	//   More points and wider paths in an attempt to get around the cones
	public static final Translation2d barrelStart = new Translation2d(1.01, -2.34);
	public static final Translation2d barrelOne = new Translation2d(4.1, -2.24);
	public static final Translation2d barrelTwo = new Translation2d(4.78, -3.11);
	public static final Translation2d barrelThree = new Translation2d(3.8, -3.86);
	public static final Translation2d barrelFour = new Translation2d(2.89, -3.01);
	public static final Translation2d barrelFive = new Translation2d(3.76, -2.25);
	public static final Translation2d barrelSix = new Translation2d(6.81, -2.05);
	public static final Translation2d barrelSeven = new Translation2d(6.81, -0.89);
	public static final Translation2d barrelEight = new Translation2d(5.31, -1.14);
	public static final Translation2d barrelNine = new Translation2d(6.34, -3.08);
	public static final Translation2d barrelTen = new Translation2d(8, -3.67);
	public static final Translation2d barrelEleven = new Translation2d(8.53, -2.86);
	public static final Translation2d barrelTwelve = new Translation2d(7.7, -2.22);
	public static final Translation2d barrelEnd = new Translation2d(1.01, -2.34);
	
	public static List<Translation2d> interiorWaypointsBarrelPath = List.of(barrelOne, barrelTwo, barrelThree,
			barrelFour, barrelFive, barrelSix, barrelSeven, barrelEight, barrelNine, barrelTen, barrelEleven,
			barrelTwelve);

	public static final Trajectory barrelPathTrajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(barrelStart, new Rotation2d(0)), interiorWaypointsBarrelPath,
			new Pose2d(barrelStart, new Rotation2d(-180)), config);

	public static Translation2d slalomPointOne = new Translation2d(0.794, -3.907);
	public static Translation2d slalomPointTwo = new Translation2d(2.042, -3.715);
	public static Translation2d slalomPointThree = new Translation2d(2.761, -2.65);
	public static Translation2d slalomPointFour = new Translation2d(4.429, -1.794);
	public static Translation2d slalomPointFive = new Translation2d(6.254, -2.345);
	public static Translation2d slalomPointSix = new Translation2d(6.747, -3.076);
	public static Translation2d slalomPointSeven = new Translation2d(7.707, -3.922);
	public static Translation2d slalomPointEight = new Translation2d(8.765, -2.969);
	public static Translation2d slalomPointNine = new Translation2d(7.658, -2.024);
	public static Translation2d slalomPointTen = new Translation2d(7, -2.766);
	public static Translation2d slalomPointEleven = new Translation2d(6.5, -3.767);
	public static Translation2d slalomPointTwelve = new Translation2d(3.6, -3.935);
	public static Translation2d slalomPointThirteen = new Translation2d(1.875, -2.530);
	public static Translation2d slalomPointThirteen2 = new Translation2d(1, -2.2);
	public static Translation2d slalomPointFourteen = new Translation2d(0.607, -2.147);

	public final static List<Translation2d> interiorWaypointsSlalomPath = List.of(slalomPointTwo, slalomPointThree,
			slalomPointFour, slalomPointFive, slalomPointSix, slalomPointSeven, slalomPointEight, slalomPointNine,
			slalomPointTen, slalomPointEleven, slalomPointTwelve, slalomPointThirteen, slalomPointThirteen2);

	public static final Trajectory slalomPathTrajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(slalomPointOne, new Rotation2d(0)), interiorWaypointsSlalomPath,
			new Pose2d(slalomPointFourteen, new Rotation2d(180)), config);

	// Bounce Route points
	// public static final Translation2d bounceStart = new Translation2d(0.91, -2.29);
	// public static final Translation2d bounceStart2 = new Translation2d(2, -2.2);
	// public static final Translation2d bounceStar1 = new Translation2d(2.29, -0.69);
	// public static final Translation2d bouncePoint1a = new Translation2d(3.5, -3.81);
	// public static final Translation2d bouncePoint1b = new Translation2d(4.45, -3.81);
	// public static final Translation2d bounceStar2 = new Translation2d(4.8, -0.69);
	// public static final Translation2d bouncePoint2 = new Translation2d(5.03, -3.66);
	// public static final Translation2d bouncePoint3 = new Translation2d(7, -3.02);
	// public static final Translation2d bounceStar3 = new Translation2d(7, -0.69);
	// public static final Translation2d bounceEnd = new Translation2d(8.23, -2);

	// public static List<Translation2d> interiorWaypointsBouncePath = List.of(bounceStart2, bounceStar1, bouncePoint1a, bouncePoint1b, bounceStar2,
	//		bouncePoint2, bouncePoint3, bounceStar3);

  	// public static final Trajectory bouncePathTrajectory = TrajectoryGenerator.generateTrajectory(
  	// 		new Pose2d(bounceStart, new Rotation2d(0)), interiorWaypointsBouncePath,
  	// 		new Pose2d(bounceEnd, new Rotation2d(-180)), config);

  	// Bounce 3 Route points
  	//   More points to better round curves
	public static final Translation2d bounceStart = new Translation2d(1.25, -2.27);
	public static final Translation2d bounceOne = new Translation2d(2.1, -1.68);
	public static final Translation2d bounceTwo = new Translation2d(2.32, -0.7);
	public static final Translation2d bounceThree = new Translation2d(2.83, -2.51);
	public static final Translation2d bounceFour = new Translation2d(3.25, -3.58);
	public static final Translation2d bounceFive = new Translation2d(4.23, -3.73);
	public static final Translation2d bounceSix = new Translation2d(4.58, -2.51);
	public static final Translation2d bounceSeven = new Translation2d(4.61, -0.73);
	public static final Translation2d bounceEight = new Translation2d(4.67, -2.65);
	public static final Translation2d bounceNine = new Translation2d(5.02, -3.67);
	public static final Translation2d bounceTen = new Translation2d(6.41, -3.71);
	public static final Translation2d bounceEleven = new Translation2d(6.83, -2.32);
	public static final Translation2d bounceTweleve = new Translation2d(6.96, -0.76);
	public static final Translation2d bounceThirteen = new Translation2d(7.25, -1.98);
	public static final Translation2d bounceEnd = new Translation2d(8.33, -2.45);

	public static List<Translation2d> interiorWaypointsBouncePath = List.of(bounceOne, bounceTwo, bounceThree,
			bounceFour, bounceFive, bounceSix, bounceSeven, bounceEight, bounceNine, bounceTen, bounceEleven,
			bounceTweleve, bounceThirteen);

	public static final Trajectory bouncePathTrajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(bounceStart, new Rotation2d(0)), interiorWaypointsBouncePath,
			new Pose2d(bounceEnd, new Rotation2d(-180)), config);

	public static final Translation2d squareBeginning = new Translation2d(0, 0);

	private static final double squareX = 1.5;
	private static final double squareY = 1;
	public static final List<Pose2d> squareWaypoints = List.of(
			new Pose2d(new Translation2d(0, 0), Rotation2d.fromDegrees(0)),
			new Pose2d(new Translation2d(squareX, 0), Rotation2d.fromDegrees(0)),
			new Pose2d(new Translation2d(squareX + 0.25, squareY/2), Rotation2d.fromDegrees(90)),
			new Pose2d(new Translation2d(squareX, squareY), Rotation2d.fromDegrees(-180)),
			new Pose2d(new Translation2d(0, squareY), Rotation2d.fromDegrees(-180)),
			new Pose2d(new Translation2d(-0.25, squareY/2), Rotation2d.fromDegrees(-90)),
			new Pose2d(new Translation2d(0, 0), Rotation2d.fromDegrees(0)),
			new Pose2d(new Translation2d(0.25, 0), Rotation2d.fromDegrees(0))
			);

	public static final Trajectory squarePathTrajectory = TrajectoryGenerator.generateTrajectory(squareWaypoints,
			config);

}
