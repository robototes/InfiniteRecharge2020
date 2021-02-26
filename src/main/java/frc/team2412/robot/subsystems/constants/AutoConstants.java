package frc.team2412.robot.subsystems.constants;

import static frc.team2412.robot.subsystems.constants.AutoConstants.config;
import static frc.team2412.robot.subsystems.constants.AutoConstants.driveSub;

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
	
	
	// Barrel Route points
	public static final Translation2d barrelStart = new Translation2d(1.5, -2.632);
	public static final Translation2d barrelOne = new Translation2d(4.674, -3.403);
	public static final Translation2d barrelTwo = new Translation2d(4.174, -3.808);
	public static final Translation2d barrelThree  = new Translation2d(3.564, -3.353);
	public static final Translation2d barrelFour = new Translation2d(7.135, -1.911);
	public static final Translation2d barrelFive = new Translation2d(6.248, -1.356);
	public static final Translation2d barrelSix = new Translation2d(8.422, -3.863);
	public static final Translation2d barrelSeven = new Translation2d(8.466, -2.854);
	public static final Translation2d barrelEnd = new Translation2d(1.445, -2.099);
	
	public static List<Translation2d> interiorWaypointsBarrelPath = List.of(barrelOne, barrelTwo, barrelThree, barrelFour, barrelFive, barrelSix, barrelSeven);
	
	public final Trajectory bouncePathTrajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(barrelStart, new Rotation2d(0)), interiorWaypointsBarrelPath,
			new Pose2d(barrelEnd, new Rotation2d(0)), config);

}
