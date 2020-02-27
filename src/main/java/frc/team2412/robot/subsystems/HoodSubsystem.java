package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.commands.hood.HoodExtendCommand;
import frc.team2412.robot.commands.hood.HoodWithdrawCommand;
import frc.team2412.robot.subsystems.constants.HoodConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// @Author Rahul Singh && Sumedh Panatula

public class HoodSubsystem extends SubsystemBase implements Loggable {

	@Log(name = "Right Servo", tabName = "Hood", width = 8, height = 1, columnIndex = 0, rowIndex = 0)
	private Servo m_hoodServo1;

	@Log(name = "Left Servo", tabName = "Hood", width = 8, height = 1, columnIndex = 0, rowIndex = 1)
	private Servo m_hoodServo2;

	public HoodSubsystem(Servo hoodServo1, Servo hoodServo2) {
		this.m_hoodServo1 = hoodServo1;
		this.m_hoodServo2 = hoodServo2;
		extendServoCommand = new HoodExtendCommand(this);
		retractServoCommand = new HoodWithdrawCommand(this);
	}

	public double getServo() {
		return m_hoodServo1.get();
	}

	@Log(name = "Extend Servos Fully", tabName = "Hood", width = 2, height = 1, columnIndex = 4, rowIndex = 2)
	Command extendServoCommand;

	@Log(name = "Retract Servos Fully", tabName = "Hood", width = 2, height = 1, columnIndex = 4, rowIndex = 2)
	Command retractServoCommand;

	public void servoExtend() {
		m_hoodServo1.set(1 - HoodConstants.MAX_EXTENSION);
		m_hoodServo2.set(HoodConstants.MAX_EXTENSION);
	}

	public void servoWithdraw() {
		m_hoodServo1.set(1 - HoodConstants.MAX_WITHDRAWL);
		m_hoodServo2.set(HoodConstants.MAX_WITHDRAWL);

	}

	@Config.NumberSlider(max = 0.65, min = 0, name = "Set Servo Angle", tabName = "Hood Subsystem", width = 2, height = 1, columnIndex = 2, rowIndex = 0)
	public void setServo(double angle) {
		System.out.println(angle);
		m_hoodServo2.set(angle);
		m_hoodServo1.set(1 - angle);
	}

	@Override
	public void periodic() {
	}

}
