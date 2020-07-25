package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.ENCODER_TICKS_PER_SECOND;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.encoderTicksPerRevolution;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kGyroReversed;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.lowGearRatio;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.metersPerWheelRevolution;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DriveBaseSubsystem extends SubsystemBase {

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public Gyro m_gyro;

	public Solenoid m_gearShifter;

	public double m_currentYSpeed;

	private DifferentialDriveOdometry m_odometry;

	@SuppressWarnings("unused")
	private double m_rightMotorRevolutions, m_leftMotorRevolutions;

	private boolean oneJoystickDrive = false;

	private boolean inOneJoystickDrive = oneJoystickDrive;

	// DifferentialDrive m_drive;

	public double m_driveBaseCurrentDraw;

	public DriveBaseSubsystem(Solenoid gearShifter, Gyro gyro, WPI_TalonFX leftMotor1, WPI_TalonFX leftMotor2,
			WPI_TalonFX rightMotor1, WPI_TalonFX rightMotor2) {

		m_leftMotor1 = leftMotor1;
		m_leftMotor2 = leftMotor2;
		m_rightMotor1 = rightMotor1;
		m_rightMotor2 = rightMotor2;

		m_leftMotor2.follow(leftMotor1);
		m_rightMotor2.follow(rightMotor1);

		m_rightMotor1.setInverted(true);
		m_rightMotor2.setInverted(true);

		// m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

		m_gyro = gyro;
		m_gearShifter = gearShifter;

		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;

		m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
	}

	public void drive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (oneJoystickDrive) {
			m_rightMotor1.set(rightJoystick.getY() - Math.pow(rightJoystick.getTwist(), 3));
			m_leftMotor1.set(rightJoystick.getY() + Math.pow(rightJoystick.getTwist(), 3));
		} else {

			if (button.get()) {
				m_rightMotor1.set(rightJoystick.getY());
				m_leftMotor1.set(rightJoystick.getY());
			} else {
				m_rightMotor1.set(rightJoystick.getY());
				m_leftMotor1.set(leftJoystick.getY());
			}
		}
	}

	public void oneJoystickDrive(Joystick joystick) {
		// m_drive.arcadeDrive(-1 * joystick.getY(), joystick.getTwist(), true);
	}

	public void setDriveSpeed(double forwardness, double turn) {
		// m_drive.arcadeDrive(forwardness, turn);
		// m_currentYSpeed = forwardness;
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
	}

	public void shiftToLowGear() {
		m_gearShifter.set(false);
	}

	public double getGyroHeading() {
		return Math.IEEEremainder(m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getCurrentYSpeed() {
		return m_currentYSpeed;
	}

	public double getCurrentDraw() {
		return m_driveBaseCurrentDraw;
	}

	// Periodic
	// ------------------------------------------------------------------------------

	@Override
	public void periodic() {

		m_currentYSpeed = (m_rightMotor1.get() + m_leftMotor1.get()) / -2.0;

		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition();
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition();

		// m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()),
		// (m_leftMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution,
		// (m_rightMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution);

		m_driveBaseCurrentDraw = m_rightMotor1.getStatorCurrent() + m_rightMotor2.getStatorCurrent()
				+ m_leftMotor1.getStatorCurrent() + m_leftMotor2.getStatorCurrent();

	}

	// Trajectory stuff
	// _________________________________________________________________________________________________

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(
				m_leftMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND,
				m_rightMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		m_leftMotor1.setVoltage(leftVolts);
		m_rightMotor1.setVoltage(rightVolts);
		// m_drive.feed();
	}

}