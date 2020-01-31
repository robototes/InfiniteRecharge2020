package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
@Author Rahul Singh
*/

public class FlywheelSubsystem extends SubsystemBase {

	private SpeedControllerGroup m_flywheelMotors;

	public FlywheelSubsystem(CANSparkMax flywheelMotor1, CANSparkMax flywheelMotor2) {
		flywheelMotor2.setInverted(true);
		this.m_flywheelMotors = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

	}

	public void Shoot() {
		m_flywheelMotors.set(1.0);
	}

	public void Stop() {
		m_flywheelMotors.set(0.0);
	}
}
