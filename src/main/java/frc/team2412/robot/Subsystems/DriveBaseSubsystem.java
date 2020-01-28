package frc.team2412.robot.Subsystems;

import com.robototes.math.Vector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBaseSubsystem extends SubsystemBase {

	private DifferentialDrive m_robotDrive;

	public Vector m_motion;

	public ADXRS450_Gyro m_gyro;

	private Joystick m_joystick;

	public double CurrentYSpeed;

	public DriveBaseSubsystem(DifferentialDrive robotDrive, ADXRS450_Gyro gyro, Joystick joystick) {
		m_motion = new Vector(0);
		this.m_robotDrive = robotDrive;
		this.setName("DriveBase Subsystem");
		m_gyro = gyro;
		this.m_joystick = joystick;
	}

	public void periodic() {
		m_motion = new Vector(m_gyro.getAngle() % 360);
	}

	public void drive() {
		m_robotDrive.arcadeDrive(m_joystick.getY(), m_joystick.getTwist(), true);
		CurrentYSpeed = m_joystick.getY();
	}

	public void setDriveSpeed(double forwardness, double turn) {
		m_robotDrive.arcadeDrive(forwardness, turn, true);
	}

	public double getCurrentRotation() {
		return m_motion.angle;
	}

	public double getCurrentYSpeed() {
		return CurrentYSpeed;
	}

}
