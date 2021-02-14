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

	public WPI_TalonFX leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

	public Gyro gyro;

	public Solenoid gearShifter;

	public double currentYSpeed;

	private DifferentialDriveOdometry odometry;

	private double rightMotorRevolutions, leftMotorRevolutions;

	private boolean oneJoystickDrive = false;

	private boolean inOneJoystickDrive = oneJoystickDrive;

	// DifferentialDrive drive;

	public double driveBaseCurrentDraw;

	public DriveBaseSubsystem(Solenoid gearShifter, Gyro gyro, WPI_TalonFX leftFrontMotor, WPI_TalonFX leftBackMotor,
			WPI_TalonFX rightFrontMotor, WPI_TalonFX rightBackMotor) {

		this.leftFrontMotor = leftFrontMotor;
		this.leftBackMotor = leftBackMotor;
		this.rightFrontMotor = rightFrontMotor;
		this.rightBackMotor = rightBackMotor;

		this.leftBackMotor.follow(leftFrontMotor);
		this.rightBackMotor.follow(rightFrontMotor);

		this.rightFrontMotor.setInverted(true);
		this.rightBackMotor.setInverted(true);

		// drive = new DifferentialDrive(leftMotors, rightMotors);

		this.gyro = gyro;
		this.gearShifter = gearShifter;

		rightMotorRevolutions = rightFrontMotor.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;
		leftMotorRevolutions = leftFrontMotor.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;

		odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
	}

	public void drive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (oneJoystickDrive) {
			rightFrontMotor.set(rightJoystick.getY() - Math.pow(rightJoystick.getTwist(), 3));
			leftFrontMotor.set(rightJoystick.getY() + Math.pow(rightJoystick.getTwist(), 3));
		} else {

			if (button.get()) {
				rightFrontMotor.set(rightJoystick.getY());
				leftFrontMotor.set(rightJoystick.getY());
			} else {
				rightFrontMotor.set(rightJoystick.getY());
				leftFrontMotor.set(leftJoystick.getY());
			}
		}
	}

	public void oneJoystickDrive(Joystick joystick) {
		// drive.arcadeDrive(-1 * joystick.getY(), joystick.getTwist(), true);
	}

	public void setDriveSpeed(double forwardness, double turn) {
		// drive.arcadeDrive(forwardness, turn);
		// currentYSpeed = forwardness;
	}

	public void angleDrive(double angle) {
		double startAngle = gyro.getAngle();
		if (angle > 0) {
			if (gyro.getAngle() <= startAngle) {
				setDriveSpeed(0, 1);
			} else {
				setDriveSpeed(0, 0);
			}
		} else {
			if (gyro.getAngle() >= startAngle) {
				setDriveSpeed(0, -1);
			} else {
				setDriveSpeed(0, 0);
			}
		}
	}

	public void shiftToHighGear() {
		gearShifter.set(true);
	}

	public void shiftToLowGear() {
		gearShifter.set(false);
	}

	public double getGyroHeading() {
		return Math.IEEEremainder(gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getCurrentYSpeed() {
		return currentYSpeed;
	}

	public double getCurrentDraw() {
		return driveBaseCurrentDraw;
	}

	// Periodic
	// ------------------------------------------------------------------------------

	@Override
	public void periodic() {

		currentYSpeed = (rightFrontMotor.get() + leftFrontMotor.get()) / -2.0;

		rightMotorRevolutions = rightFrontMotor.getSelectedSensorPosition();
		leftMotorRevolutions = leftFrontMotor.getSelectedSensorPosition();

		// odometry.update(Rotation2d.fromDegrees(gyro.getAngle()),
		// (leftMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution,
		// (rightMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution);

		driveBaseCurrentDraw = rightFrontMotor.getStatorCurrent() + rightBackMotor.getStatorCurrent()
				+ leftFrontMotor.getStatorCurrent() + leftBackMotor.getStatorCurrent();

	}

	// Trajectory stuff
	// _________________________________________________________________________________________________

	public Pose2d getPose() {
		return odometry.getPoseMeters();
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(
				leftFrontMotor.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND,
				rightFrontMotor.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		leftFrontMotor.setVoltage(leftVolts);
		rightFrontMotor.setVoltage(rightVolts);
		// drive.feed();
	}

}