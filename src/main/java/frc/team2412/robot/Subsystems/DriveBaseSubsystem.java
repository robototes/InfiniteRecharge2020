package frc.team2412.robot.Subsystems;

import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.robototes.math.Vector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
//mport edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.DriveBaseConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public SpeedControllerGroup m_leftMotors, m_rightMotors;

	public DifferentialDrive m_drive;

	public Vector m_motion;

	public ADXRS450_Gyro m_gyro;

	public SimpleMotorFeedforward m_simpleMotorFeedforward = new SimpleMotorFeedforward(DriveBaseConstants.ksVolts,
			DriveBaseConstants.kvVoltSecondsPerMeter, DriveBaseConstants.kaVoltSecondsSquaredPerMeter);

	public DifferentialDriveVoltageConstraint m_autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
			m_simpleMotorFeedforward, DriveBaseConstants.kDriveKinematics, DriveBaseConstants.MAX_VOLTAGE);

	TrajectoryConfig m_trajectoryConfiguration = new TrajectoryConfig(DriveBaseConstants.kMaxSpeedMetersPerSecond,
			DriveBaseConstants.kMaxAccelerationMetersPerSecondSquared)
					.setKinematics(DriveBaseConstants.kDriveKinematics).addConstraint(m_autoVoltageConstraint);

	@Log
	public double m_currentYSpeed;

	private DifferentialDriveOdometry m_odometry;

	public DriveBaseSubsystem(ADXRS450_Gyro gyro, WPI_TalonFX leftMotor1, WPI_TalonFX leftMotor2,
			WPI_TalonFX rightMotor1, WPI_TalonFX rightMotor2) {
		this.setName("DriveBase Subsystem");
		m_motion = new Vector(0);
		m_gyro = gyro;

		m_leftMotor1 = leftMotor1;
		m_leftMotor2 = leftMotor2;
		m_rightMotor1 = rightMotor1;
		m_rightMotor2 = rightMotor2;
		m_leftMotor2.follow(leftMotor1);
		m_rightMotor2.follow(rightMotor1);

		m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2);
		m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2);
		m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

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

	public double getCurrentRotation() {
		return m_gyro.getAngle();
	}

	public double getCurrentYSpeed() {
		return m_currentYSpeed;
	}

	@Config
	public void setDriveSpeed(double forwardness, double turn) {
		m_rightMotor1.set(forwardness - turn);
		m_leftMotor1.set(forwardness + turn);
		m_currentYSpeed = forwardness;
	}

	public void angleDrive(double angle) {
		double startAngle = m_gyro.getAngle();
		if (angle > 0) {
			while (m_gyro.getAngle() <= startAngle) {
				setDriveSpeed(0, 1);
			}
			setDriveSpeed(0, 0);
		} else {
			while (m_gyro.getAngle() >= startAngle) {
				setDriveSpeed(0, -1);
			}
			setDriveSpeed(0, 0);
		}
	}

	public int getEncoderValue(WPI_TalonFX motor) {
		return motor.getSelectedSensorPosition();
	}

	// -----------------------------------------------------------------------------------------------
	// Trajectory stuff
	// -----------------------------------------------------------------------------------------------

	public void trajectoryDrive() {
		// Trajectory trajectory = new Trajectory(null);
	}

	public double getFeetToMeters(double feet) {
		return feet / 0.3048;
	}

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		m_leftMotors.setVoltage(leftVolts);
		m_rightMotors.setVoltage(-rightVolts);
		m_drive.feed();
	}

	public double getDistanceMoved(WPI_TalonFX motor) {
		double distance = getFeetToMeters(getEncoderValue(motor) * DriveBaseConstants.feetPerEncoderRevolution);
		return distance;
	}

	public Trajectory makeTrajectory(double x1, double y1, double startHeading, double x2, double y2,
			double finalHeading, double vertexX, double vertexY) {

		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
				new Pose2d(x1, y1, new Rotation2d(Math.toRadians(startHeading))),
				List.of(new Translation2d(vertexX, vertexY)),
				new Pose2d(x2, y2, new Rotation2d(Math.toRadians(startHeading))), m_trajectoryConfiguration);

		return trajectory;
	}

	@Override
	public void periodic() {
		m_motion = new Vector(m_gyro.getAngle() % 360);
		m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getDistanceMoved(m_leftMotor1),
				getDistanceMoved(m_rightMotor1));
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds();
	}
	
}
