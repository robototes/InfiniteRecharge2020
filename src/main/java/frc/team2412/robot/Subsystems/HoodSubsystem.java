package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.HoodConstants;

// @Author Rahul Singh && Sumedh Panatula

public class HoodSubsystem extends SubsystemBase {

	private Servo m_hoodServo;

	public HoodSubsystem(Servo hoodServo) {
		this.m_hoodServo = hoodServo;
	}

	public double getServo() {
		return m_hoodServo.get();
	}

	public void servoExtend() {
		m_hoodServo.set(HoodConstants.MaxExtension);
	}

	public void servoWithdraw() {
		m_hoodServo.set(HoodConstants.MaxWithdrawal);
	}

	public void setServo(double angle) {
		m_hoodServo.set(angle);
	}

}
