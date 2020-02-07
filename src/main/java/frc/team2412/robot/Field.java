package frc.team2412.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Field implements Loggable {

	private static final Double RATIO_OF_ROTATIONS = 1.0;

	@Log
	private double robotX;
	@Log
	private double robotY = 70;

	private double leftEncoderVal = 0;
	private double rightEncoderVal = 0;

	@Log
	private double gyroVal;

	private DriveBaseSubsystem m_driveBaseSubsystem;
//	private TurretSubsystem m_turretSubsystem;
	private ADXRS450_Gyro m_gyro;

	public Field(DriveBaseSubsystem driveBase, TurretSubsystem turret, double x) {
		m_driveBaseSubsystem = driveBase;
//	m_turretSubsystem = turret;
		robotX = x;
		gyroVal = m_gyro.getAngle();
//robotY = Math.cos(m_turretSubsystem.getAngle) * m_turretSubsystem.getDistance;
	}

	public void calculateXY() {
		// Get heading
		double gyroChange = m_gyro.getAngle() % 360 - gyroVal;
		double leftEncoderChange = leftEncoderVal
				+ m_driveBaseSubsystem.getEncoderValue(m_driveBaseSubsystem.m_leftMotor1);
		double rightEncoderChange = rightEncoderVal
				+ m_driveBaseSubsystem.getEncoderValue(m_driveBaseSubsystem.m_rightMotor1);

		double movement = (getFeetMovement(leftEncoderChange) + getFeetMovement(rightEncoderChange)) / 2;

		// Check if one is positive is the other is negative i.e. turning
		if (leftEncoderChange < 0 && rightEncoderChange > 0 || leftEncoderChange > 0 && rightEncoderChange < 0) {
		} else {

			if (gyroChange > 0 && gyroChange < 90) {
				robotY += Math.cos(gyroChange) * movement;
				robotX += Math.sin(gyroChange) * movement;
			} else if (gyroChange > 90) {
				robotY -= Math.cos(gyroChange) * movement;
				robotX += Math.sin(gyroChange) * movement;
			} else if (gyroChange < 0 && gyroChange > -90) {
				robotY += Math.cos(gyroChange) * movement;
				robotX -= Math.sin(gyroChange) * movement;
			} else if (gyroChange < -90) {
				robotY -= Math.cos(gyroChange) * movement;
				robotX -= Math.sin(gyroChange) * movement;
			}

		}

		gyroVal = m_gyro.getAngle();
		leftEncoderVal = m_driveBaseSubsystem.getEncoderValue(m_driveBaseSubsystem.m_leftMotor1);
		rightEncoderVal = m_driveBaseSubsystem.getEncoderValue(m_driveBaseSubsystem.m_rightMotor1);

	}

	public boolean canWeGetItIn() {
		double hyp = Math.sqrt(Math.pow(robotY, 2) + Math.pow(robotX, 2));
		return hyp <= 45;
	}

	public double getFeetMovement(Double Change) {
		return Change / RATIO_OF_ROTATIONS;
	}

}
