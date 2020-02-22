package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.HoodConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// @Author Rahul Singh && Sumedh Panatula

public class HoodSubsystem extends SubsystemBase implements Loggable {

	@Log(name = "Right Servo", tabName = "Hood Subsystem")
	private Servo m_hoodServo1;

	@Log(name = "Left Servo", tabName = "Hood Subsystem")
	private Servo m_hoodServo2;

	public HoodSubsystem(Servo hoodServo1, Servo hoodServo2) {
		this.m_hoodServo1 = hoodServo1;
		this.m_hoodServo2 = hoodServo2;
	}

	public double getServo() {
		return m_hoodServo1.get();
	}

	@Config.ToggleButton(name = "Extend Servos Fully", tabName = "Hood Subsystem")
	public void servoExtend() {
		m_hoodServo1.set(1-HoodConstants.MaxExtension);
		m_hoodServo2.set(HoodConstants.MaxExtension);
	}

	@Config.ToggleButton(name = "Retract Servos Fully", tabName = "Hood Subsystem")
	public void servoWithdraw() {
		m_hoodServo1.set(1-HoodConstants.MaxWithdrawal);
		m_hoodServo2.set(HoodConstants.MaxWithdrawal);

	}

	@Config.NumberSlider(max = 0.65, min = 0, name ="Set Servo Angle", tabName = "Hood Subsystem")
	public void setServo(double angle) {
		System.out.println(angle);
		m_hoodServo2.set(angle);
		m_hoodServo1.set(1-angle);
	}

}
