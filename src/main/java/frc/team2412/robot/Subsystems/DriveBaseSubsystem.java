package frc.team2412.robot.Subsystems;

import com.robototes.math.Vector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.DriveCommands.DriveCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	private DifferentialDrive m_robotDrive;
	private SpeedControllerGroup m_leftMotors;
	private SpeedControllerGroup m_rightMotors;

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public Vector m_motion;

	public ADXRS450_Gyro m_gyro;

	@Log
	public double m_currentYSpeed;

	public DriveBaseSubsystem(DifferentialDrive robotDrive, ADXRS450_Gyro gyro, Joystick joystick,
			WPI_TalonFX leftMotor1, WPI_TalonFX leftMotor2, WPI_TalonFX rightMotor1, WPI_TalonFX rightMotor2) {
		m_motion = new Vector(0);
		this.m_robotDrive = robotDrive;
		this.setName("DriveBase Subsystem");
		m_gyro = gyro;

		m_leftMotor1 = leftMotor1;
		m_leftMotor2 = leftMotor2;
		m_rightMotor1 = rightMotor1;
		m_rightMotor2 = rightMotor2;

		setDefaultCommand(new DriveCommand(this, joystick));
	}

	public void drive(Joystick joystick) {
		m_robotDrive.arcadeDrive(joystick.getY(), joystick.getTwist(), true);
		m_currentYSpeed = joystick.getY();
	}

	public double getCurrentRotation() {
		return m_gyro.getAngle();
	}

	public double getCurrentYSpeed() {
		return m_currentYSpeed;
	}

	@Override
	public void periodic() {
		m_motion = new Vector(m_gyro.getAngle() % 360);
	}

	@Config
	public void setDriveSpeed(double forwardness, double turn) {
		m_robotDrive.arcadeDrive(forwardness, turn, true);
	}

	public void twoJoystickDrive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (button.get()) {
			double averageY = (rightJoystick.getY() + leftJoystick.getY()) / 2;
			m_rightMotors.set(averageY);
			m_leftMotors.set(averageY);
			m_currentYSpeed = averageY;
		} else {
			m_rightMotors.set(rightJoystick.getY());
			m_leftMotors.set(leftJoystick.getY());
		}
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
}
