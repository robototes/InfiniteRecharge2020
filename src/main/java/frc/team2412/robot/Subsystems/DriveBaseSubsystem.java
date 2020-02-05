package frc.team2412.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.robototes.math.Vector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public Vector m_motion;

	public ADXRS450_Gyro m_gyro;

	@Log
	public double m_currentYSpeed;

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

	@Override
	public void periodic() {
		m_motion = new Vector(m_gyro.getAngle() % 360);
	}

	@Config
	public void setDriveSpeed(double forwardness, double turn) {
		m_rightMotor1.set(forwardness - turn);
		m_leftMotor1.set(forwardness + turn);
		m_currentYSpeed = forwardness;
	}

	public void twoJoystickDrive(Joystick rightJoystick, Joystick leftJoystick, Button button) {

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
