package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.FlywheelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class FlywheelSubsystem extends SubsystemBase implements Loggable {

	private CANSparkMax m_flywheelLeftMotor;
	private CANEncoder m_leftEncoder;
	private CANPIDController m_pidLeftController;

	private CANSparkMax m_flywheelRightMotor;
	private CANEncoder m_rightEncoder;
	private CANPIDController m_pidRightController;

	public FlywheelSubsystem(CANSparkMax flywheelLeftMotor, CANSparkMax flywheelRightMotor) {
		m_flywheelLeftMotor = flywheelLeftMotor;
		m_flywheelRightMotor = flywheelRightMotor;

		m_flywheelRightMotor.setInverted(true);
		
		configureMotor(m_flywheelLeftMotor, m_leftEncoder, m_pidLeftController);
		configureMotor(m_flywheelRightMotor, m_rightEncoder, m_pidRightController);
		setPIDtoDefault();
	}

	private void configureMotor(CANSparkMax motor, CANEncoder encoder, CANPIDController pidController) {
		encoder = motor.getEncoder();
		pidController = m_flywheelRightMotor.getPIDController();
		motor.setIdleMode(IdleMode.kCoast);
	}
	
	@Config.NumberSlider(min = -1, max = 0, name = "Set speed", tabName = "Flywheel", width = 3, height = 1, columnIndex = 2, rowIndex = 0)
	public void setSpeed(double speed) {
		if (speed <= 0) {
			m_flywheelLeftMotor.set(speed);
			m_flywheelRightMotor.set(speed);
		}
	}
	
	public void setPIDtoDefault() {
		setP(FlywheelConstants.P);
		setI(FlywheelConstants.I);
		setD(FlywheelConstants.D);
		setFF(FlywheelConstants.FF);
	}

	public void shoot() {
		// m_flywheelLeftMotor.set(1.0);
	}

	public void stop() {
		// m_flywheelLeftMotor.set(0.0);
	}

	public double getSpeed() {
		return m_flywheelLeftMotor.get();
	}

	@Config.NumberSlider(name = "Set Flywheel speed in Meters per Second", tabName = "Flywheel", min = 0, max = 7000, width = 3, height = 1, rowIndex = 1, columnIndex = 2)
	public void setRPM(double wantedVelocity) {
//		double wantedRPM = -NEO_RPM_TO_FLYWHEEL_MPS.calculateRatio(wantedVelocity);

		m_pidLeftController.setReference(-wantedVelocity, ControlType.kVelocity);
		m_pidRightController.setReference(-wantedVelocity, ControlType.kVelocity);
	}

	@Log.NumberBar(min = 0, max = 7000, name = "Left Flywheel Motor", tabName = "Flywheel", width = 2, height = 1, rowIndex = 0, columnIndex = 0)
	public double currentLeftSpeed() {
		return m_leftEncoder.getVelocity();
	}

	@Log.NumberBar(min = 0, max = 7000, name = "Right Flywheel Motor", tabName = "Flywheel", width = 2, height = 1, rowIndex = 0, columnIndex = 0)
	public double currentRightSpeed() {
		return -m_rightEncoder.getVelocity();
	}

	@Config(defaultValueNumeric = FlywheelConstants.P, name = "Set Flywheel P", tabName = "Flywheel", width = 1, height = 1, columnIndex = 5, rowIndex = 0)
	public void setP(double newP) {
		m_pidLeftController.setP(newP);
		m_pidRightController.setP(newP);
	}

	@Config(defaultValueNumeric = FlywheelConstants.I, name = "Set Flywheel I", tabName = "Flywheel", width = 1, height = 1, columnIndex = 5, rowIndex = 1)
	public void setI(double newI) {
		m_pidLeftController.setI(newI);
		m_pidRightController.setI(newI);
	}

	@Config(defaultValueNumeric = FlywheelConstants.D, name = "Set Flywheel D", tabName = "Flywheel", width = 1, height = 1, columnIndex = 5, rowIndex = 2)
	public void setD(double newD) {
		m_pidLeftController.setD(newD);
		m_pidRightController.setD(newD);
	}

	@Config(defaultValueNumeric = FlywheelConstants.FF, name = "Set Flywheel FF", tabName = "Flywheel", width = 1, height = 1, columnIndex = 5, rowIndex = 3)
	public void setFF(double newFF) {
		m_pidLeftController.setFF(newFF);
		m_pidRightController.setFF(newFF);
	}

	public double getCurrentDraw() {
		return m_flywheelLeftMotor.getOutputCurrent() + m_flywheelRightMotor.getOutputCurrent();
	}

}
