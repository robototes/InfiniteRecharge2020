package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.encoderTicksPerRevolution;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.lowGearRatio;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kDriveKinematics;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kGyroReversed;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kMaxAccelerationMetersPerSecondSquared;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kMaxSpeedMetersPerSecond;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kPDriveVel;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kRamseteB;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kRamseteZeta;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kaVoltSecondsSquaredPerMeter;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.ksVolts;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kvVoltSecondsPerMeter;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.metersPerWheelRevolution;

import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public Gyro m_gyro;

	public Solenoid m_gearShifter;

	@Log
	public double m_currentYSpeed;

	public SpeedControllerGroup m_leftMotors, m_rightMotors;

	public DifferentialDrive m_drive;

	private DifferentialDriveOdometry m_odometry;

	private double m_rightMotorRevolutions, m_leftMotorRevolutions;

	private double m_headingToGoal = 180;

	public DriveBaseSubsystem(Solenoid gearShifter, Gyro gyro, WPI_TalonFX leftMotor1, WPI_TalonFX leftMotor2,
			WPI_TalonFX rightMotor1, WPI_TalonFX rightMotor2) {

		m_leftMotor1 = leftMotor1;
		m_leftMotor2 = leftMotor2;
		m_rightMotor1 = rightMotor1;
		m_rightMotor2 = rightMotor2;
		m_leftMotor2.follow(leftMotor1);
		m_rightMotor2.follow(rightMotor1);
		
		m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2);
		m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2);
		m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

		m_gyro = gyro;
		m_gearShifter = gearShifter;

		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;

		m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()));
	}

	public void drive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (button.get()) {
			double averageY = (rightJoystick.getY() + leftJoystick.getY()) / 2;
			m_rightMotor1.set(averageY);
			m_leftMotor1.set(averageY);
			m_currentYSpeed = averageY;
		} else {
			m_rightMotor1.set(rightJoystick.getY());
			m_leftMotor1.set(leftJoystick.getY());
		}
		m_currentYSpeed = (rightJoystick.getY() + leftJoystick.getY()) / 2;
	}

	public void oneJoystickDrive(Joystick joystick) {
		m_drive.arcadeDrive(-1 * joystick.getY(), joystick.getTwist(), true);
	}

	@Config
	public void setDriveSpeed(double forwardness, double turn) {
		m_drive.arcadeDrive(forwardness, turn);
		m_currentYSpeed = forwardness;
	}

	public void angleDrive(double angle) {
		double startAngle = m_gyro.getAngle();
		if (angle > 0) {
			if (m_gyro.getAngle() <= startAngle) {
				setDriveSpeed(0, 1);
			} else {
				setDriveSpeed(0, 0);
			}
		} else {
			if (m_gyro.getAngle() >= startAngle) {
				setDriveSpeed(0, -1);
			} else {
				setDriveSpeed(0, 0);
			}
		}
	}

	public void shiftToHighGear() {
		m_gearShifter.set(true);
		RobotState.m_gearState = RobotState.GearboxState.HIGH;
	}

	public void shiftToLowGear() {
		m_gearShifter.set(false);
		RobotState.m_gearState = RobotState.GearboxState.LOW;
	}

	public double getGyroHeading() {
		return m_gyro.getAngle();
	}

	public double getCurrentYSpeed() {
		return m_currentYSpeed;
	}

	public double getHeadingToGoal() {
		return m_headingToGoal;
	}

	@Override
	public void periodic() {
		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition();
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition();

		m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()),
				(m_leftMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) * metersPerWheelRevolution,
				(m_rightMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) * metersPerWheelRevolution);

		m_headingToGoal = (m_headingToGoal + m_gyro.getAngle()) % 360;
	}

	// Trajectory stuff
	// _________________________________________________________________________________________________

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(
				m_leftMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio * metersPerWheelRevolution * 10,
				m_rightMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio * metersPerWheelRevolution * 10);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		m_leftMotors.setVoltage(leftVolts);
		m_rightMotors.setVoltage(-rightVolts);
		m_drive.feed();
	}

	public double getHeading() {
		return Math.IEEEremainder(m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getTurnRate() {
		return m_gyro.getRate() * (kGyroReversed ? -1.0 : 1.0);
	}

	// Create a voltage constraint to ensure we don't accelerate too fast
	DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
			new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), kDriveKinematics,
			10);

	// Create config for trajectory
	public TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond,
			kMaxAccelerationMetersPerSecondSquared)
					// Add kinematics to ensure max speed is actually obeyed
					.setKinematics(kDriveKinematics)
					// Apply the voltage constraint
					.addConstraint(autoVoltageConstraint);

	SimpleMotorFeedforward simpleMotorFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter,
			kaVoltSecondsSquaredPerMeter);

	RamseteController ramseteControlller = new RamseteController(kRamseteB, kRamseteZeta);

	PIDController pidController = new PIDController(kPDriveVel, 0, 0);

	public Command getAutonomousCommand() {

		DriveBaseSubsystem thisSub = this;

		// An example trajectory to follow. All units in meters.
		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through these two interior waypoints, making an 's' curve path
				List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
				// End 3 meters straight ahead of where we started, facing forward
				new Pose2d(3, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveThreeMetersForwardFromStartCommand() {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + 1.5, 0)),
				new Pose2d(currentTranslation.getX() + 3, 0, currentPose.getRotation()), config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	@Config
	public Command getMoveCertainAmountCommand(double finalX, double finalY) {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + (finalX / 2), finalY / 2)),
				new Pose2d(currentTranslation.getX() + finalX, finalY, currentPose.getRotation()), config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	// GetPose and then get translation and then get x and y and rotation.
}
