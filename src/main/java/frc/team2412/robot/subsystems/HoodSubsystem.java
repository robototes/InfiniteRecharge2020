package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.HoodConstants;

public class HoodSubsystem extends SubsystemBase {

	private Servo m_hoodServo1;

	private Servo m_hoodServo2;

	public HoodSubsystem(Servo hoodServo1, Servo hoodServo2) {
		this.m_hoodServo1 = hoodServo1;
		this.m_hoodServo2 = hoodServo2;
	}

	public double getServo() {
		return m_hoodServo2.get();
	}

	public void servoExtend() {
		setServo(HoodConstants.MAX_EXTENSION);
	}

	public void servoWithdraw() {
		setServo(HoodConstants.MAX_WITHDRAWL);
	}

	public void setServo(double angle) {
		angle = Math.min(angle, HoodConstants.MAX_EXTENSION);
		m_hoodServo1.set(1 - angle);
		m_hoodServo2.set(angle);
	}

	public void add(double increment) {
		setServo(getServo() + increment);
	}

}
