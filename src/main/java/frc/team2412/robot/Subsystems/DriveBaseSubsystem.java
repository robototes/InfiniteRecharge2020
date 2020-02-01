package frc.team2412.robot.Subsystems;

import com.robototes.math.Vector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.DriveCommands.DriveCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	private DifferentialDrive m_robotDrive;

	public Vector m_motion;

	public ADXRS450_Gyro m_gyro;

	@Log
	public double CurrentYSpeed;

	public DriveBaseSubsystem(DifferentialDrive robotDrive, ADXRS450_Gyro gyro, Joystick joystick) {
		m_motion = new Vector(0);
		this.m_robotDrive = robotDrive;
		this.setName("DriveBase Subsystem");
		m_gyro = gyro;

		setDefaultCommand(new DriveCommand(this, joystick));
	}

	public void drive(Joystick joystick) {
		m_robotDrive.arcadeDrive(joystick.getY(), joystick.getTwist(), true);
		CurrentYSpeed = joystick.getY();
	}

	public double getCurrentRotation() {
		return m_motion.angle;
	}

	public double getCurrentYSpeed() {
		return CurrentYSpeed;
	}

	@Override
	public void periodic() {
		m_motion = new Vector(m_gyro.getAngle() % 360);
	}

	@Config
	public void setDriveSpeed(double forwardness, double turn) {
		m_robotDrive.arcadeDrive(forwardness, turn, true);
	}

}
