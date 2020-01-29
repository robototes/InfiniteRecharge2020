package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IntakeConstants;
import frc.team2412.robot.Subsystems.constants.IntakeConstants.IntakeLastMotor;

public class IntakeMotorOnOffSubsystem extends SubsystemBase {

	private CANSparkMax m_intakeFrontMotor;
	private CANSparkMax m_intakeBackMotor;
	private SpeedControllerGroup m_intakes = new SpeedControllerGroup(m_intakeFrontMotor, m_intakeBackMotor);

	public IntakeLastMotor m_lastMotor = IntakeLastMotor.BOTH;

	public IntakeMotorOnOffSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		this.m_intakeFrontMotor = frontMotor;
		this.m_intakeBackMotor = backMotor;
	}

	public void intakeOn() {
		m_intakes.set(1);
		m_lastMotor = IntakeLastMotor.BOTH;
	}

	public void intakeOff() {
		m_intakes.set(0);
	}

	public void frontIntakeOn() {
		m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.FRONT;
	}

	public void frontIntakeOff() {
		m_intakeFrontMotor.set(0);
	}

	public void backIntakeOn() {
		m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.BACK;
	}

	public void backIntakeOff() {
		m_intakeBackMotor.set(0);
	}

	public void frontIntakeOnBackIntakeOff() {
		m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_intakeBackMotor.set(0);
		m_lastMotor = IntakeLastMotor.FRONT;
	}

	public void frontIntakeOffBackIntakeOn() {
		m_intakeFrontMotor.set(0);
		m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.BACK;
	}

	public IntakeLastMotor getLastMotor() {
		return m_lastMotor;
	}

	public void setIntake(double speed) {
		m_intakes.set(speed);
	}

}
