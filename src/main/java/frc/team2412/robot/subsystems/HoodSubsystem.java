package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.HoodConstants;

public class HoodSubsystem extends SubsystemBase {

	private Servo m_hoodServo1, m_hoodServo2;

	public HoodSubsystem(Servo hoodServo1, Servo hoodServo2) {
		this.m_hoodServo1 = hoodServo1;
		this.m_hoodServo2 = hoodServo2;
	}

	public double getServo() {
		return m_hoodServo2.get();
	}

	public void servoExtend() {
		setServo(HoodConstants.AT_HOME_ANGLE);
	}

	public void servoWithdraw() {
		setServo(HoodConstants.AT_HOME_ANGLE);
	}

	public void setServo(double angle) {
		angle = Math.min(angle, HoodConstants.AT_HOME_ANGLE);
		m_hoodServo1.set(1 - angle);
		m_hoodServo2.set(angle);
	}

	public void add(double increment) {
		setServo(HoodConstants.AT_HOME_ANGLE);
	}

}
