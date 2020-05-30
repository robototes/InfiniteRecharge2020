package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.IntakeDirection;
import frc.team2412.robot.subsystems.constants.IntakeConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeOnOffSubsystem extends SubsystemBase implements Loggable {

	@Log.NumberBar(tabName = "Intake", min = -1, max = 1, methodName = "get")
	private final CANSparkMax m_intakeFrontMotor;
	@Log.NumberBar(tabName = "Intake", min = -1, max = 1, methodName = "get")
	private final CANSparkMax m_intakeBackMotor;

	private final SpeedControllerGroup m_intakeMotorGroup;

	public IntakeOnOffSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		m_intakeFrontMotor = frontMotor;
		m_intakeBackMotor = backMotor;
		m_intakeMotorGroup = new SpeedControllerGroup(m_intakeFrontMotor, m_intakeBackMotor);

		m_intakeBackMotor.setInverted(true);
		m_intakeFrontMotor.setInverted(true);
	}

	public void set(CANSparkMax motor, double speed, IntakeDirection direction) {
		motor.set(speed);
		RobotState.m_intakeDirection = direction;
	}

	public void backIntakeOff() {
		set(m_intakeBackMotor, 0, IntakeDirection.NONE);
	}

	public void backIntakeIn() {
		set(m_intakeBackMotor, IntakeConstants.MAX_INTAKE_SPEED, IntakeDirection.BACK);
	}

	public void backIntakeOut() {
		set(m_intakeBackMotor, -IntakeConstants.MAX_INTAKE_SPEED, IntakeDirection.BACK);
	}

	public void frontIntakeOff() {
		set(m_intakeFrontMotor, 0, IntakeDirection.NONE);
	}

	public void frontIntakeIn() {
		set(m_intakeFrontMotor, IntakeConstants.MAX_INTAKE_SPEED, IntakeDirection.FRONT);
	}

	public void frontIntakeOut() {
		set(m_intakeFrontMotor, -IntakeConstants.MAX_INTAKE_SPEED, IntakeDirection.FRONT);
	}

	public void intakeOff() {
		backIntakeOff();
		frontIntakeOff();
	}

	public void intakeIn() {
		backIntakeIn();
		frontIntakeIn();
	}

	@Config.NumberSlider(min = -1, max = 1, tabName = "Intake")
	public void setIntake(double speed) {
		m_intakeMotorGroup.set(speed);
	}

	public double getCurrentDraw() {
		return m_intakeBackMotor.getOutputCurrent() + m_intakeFrontMotor.getOutputCurrent();
	}

}
