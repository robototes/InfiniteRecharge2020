package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IntakeConstants;
import frc.team2412.robot.Subsystems.constants.IntakeConstants.IntakeLastMotor;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeOnOffSubsystem extends SubsystemBase implements Loggable {

	@Log
	private CANSparkMax m_intakeFrontMotor;
	@Log
	private CANSparkMax m_intakeBackMotor;
	private SpeedControllerGroup m_intakeMotorGroup = new SpeedControllerGroup(m_intakeFrontMotor, m_intakeBackMotor);
	@Log
	public IntakeLastMotor m_lastMotor = IntakeLastMotor.BOTH;

	public IntakeOnOffSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		this.m_intakeFrontMotor = frontMotor;
		this.m_intakeBackMotor = backMotor;
	}

	public void backIntakeOff() {
		m_intakeBackMotor.set(0);
	}

	public void backIntakeOn() {
		m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.BACK;
	}

	public void frontIntakeOff() {
		m_intakeFrontMotor.set(0);
	}

	public void frontIntakeOffBackIntakeOn() {
		m_intakeFrontMotor.set(0);
		m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.BACK;
	}

	public void frontIntakeOn() {
		m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_lastMotor = IntakeLastMotor.FRONT;
	}

	public void frontIntakeOnBackIntakeOff() {
		m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
		m_intakeBackMotor.set(0);
		m_lastMotor = IntakeLastMotor.FRONT;
	}

	public IntakeLastMotor getLastMotor() {
		return m_lastMotor;
	}

	public void intakeOff() {
		m_intakeMotorGroup.set(0);
	}

	public void intakeOn() {
		m_intakeMotorGroup.set(1);
		m_lastMotor = IntakeLastMotor.BOTH;
	}

	@Config
	public void setIntake(double speed) {
		m_intakeMotorGroup.set(speed);
	}

}
