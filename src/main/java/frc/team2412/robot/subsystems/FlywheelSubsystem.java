package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.FlywheelConstants;

public class FlywheelSubsystem extends SubsystemBase  {

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

	public void setRPM(double wantedVelocity) {
//		double wantedRPM = -NEO_RPM_TO_FLYWHEEL_MPS.calculateRatio(wantedVelocity);

		m_pidLeftController.setReference(-wantedVelocity, ControlType.kVelocity);
		m_pidRightController.setReference(-wantedVelocity, ControlType.kVelocity);
	}

	public double currentLeftSpeed() {
		return m_leftEncoder.getVelocity();
	}

	public double currentRightSpeed() {
		return -m_rightEncoder.getVelocity();
	}

	public void setP(double newP) {
		m_pidLeftController.setP(newP);
		m_pidRightController.setP(newP);
	}

	public void setI(double newI) {
		m_pidLeftController.setI(newI);
		m_pidRightController.setI(newI);
	}

	public void setD(double newD) {
		m_pidLeftController.setD(newD);
		m_pidRightController.setD(newD);
	}

	public void setFF(double newFF) {
		m_pidLeftController.setFF(newFF);
		m_pidRightController.setFF(newFF);
	}

	public double getCurrentDraw() {
		return m_flywheelLeftMotor.getOutputCurrent() + m_flywheelRightMotor.getOutputCurrent();
	}

}
