package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;

import static frc.team2412.robot.subsystems.constants.IntakeConstants.MAX_INTAKE_SPEED;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IntakeConstants.*;

public class IntakeMotorSubsystem extends SubsystemBase {

	public static IntakeDirection m_intakeDirection;

	public CANSparkMax m_intakeFrontMotor;
	public CANSparkMax m_intakeBackMotor;

	public IntakeMotorSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		m_intakeFrontMotor = frontMotor;
		m_intakeBackMotor = backMotor;

		m_intakeBackMotor.setInverted(true);
		m_intakeFrontMotor.setInverted(true);
		
		setName("Intake Motor Subsystem");
	}

	public void set(CANSparkMax motor, double speed) {
		motor.set(speed);
	}

	public void backIntakeOff() {
		set(m_intakeBackMotor, 0);
	}

	public void backIntakeIn() {
		set(m_intakeBackMotor, MAX_INTAKE_SPEED);
	}

	public void backIntakeOut() {
		set(m_intakeBackMotor, -MAX_INTAKE_SPEED);
	}

	public void frontIntakeOff() {
		set(m_intakeFrontMotor, 0);
	}

	public void frontIntakeIn() {
		set(m_intakeFrontMotor, MAX_INTAKE_SPEED);
	}

	public void frontIntakeOut() {
		set(m_intakeFrontMotor, -MAX_INTAKE_SPEED);
	}

	public void intakeOff() {
		backIntakeOff();
		frontIntakeOff();
	}

	public void intakeIn() {
		backIntakeIn();
		frontIntakeIn();
	}

	public void setIntake(double speed) {
		set(m_intakeFrontMotor, speed);
		set(m_intakeBackMotor, speed);
	}

	public double getCurrentDraw() {
		return m_intakeBackMotor.getOutputCurrent() + m_intakeFrontMotor.getOutputCurrent();
	}

	public void periodic() {
		if(m_intakeFrontMotor.get() != 0 && m_intakeBackMotor.get() != 0){
			m_intakeDirection = IntakeDirection.BOTH;
		} else if(m_intakeFrontMotor.get() != 0){
			m_intakeDirection = IntakeDirection.FRONT;
		} else if (m_intakeBackMotor.get() != 0){
			m_intakeDirection = IntakeDirection.BACK;
		} else{
			m_intakeDirection = IntakeDirection.NONE;
		}
	}
}
