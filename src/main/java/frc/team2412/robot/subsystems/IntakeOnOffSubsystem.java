package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.IntakeDirection;
import frc.team2412.robot.subsystems.constants.IntakeConstants;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeLastMotor;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeOnOffSubsystem extends SubsystemBase implements Loggable {

	@Log.NumberBar(tabName = "Intake", min = -1, max = 1, methodName = "get")
	private final CANSparkMax m_intakeFrontMotor;
	@Log.NumberBar(tabName = "Intake", min = -1, max = 1, methodName = "get")
	private final CANSparkMax m_intakeBackMotor;

	private final SpeedControllerGroup m_intakeMotorGroup;
	@Log.ToString
	public IntakeLastMotor m_lastMotor = IntakeLastMotor.BOTH;

	public IntakeOnOffSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		this.m_intakeFrontMotor = frontMotor;
		this.m_intakeBackMotor = backMotor;
		this.m_intakeMotorGroup = new SpeedControllerGroup(m_intakeFrontMotor, m_intakeBackMotor);

		m_intakeBackMotor.setInverted(true);
		m_intakeFrontMotor.setInverted(true);
	}

	public void requestBackIntakeOff() {
		// We don't need to check if the intakes are disabled here, but we
		// use the request prefix for consistency (feel free to change this).
		m_intakeBackMotor.set(0);
	}

	public void requestBackIntakeIn() {
		if(!intakesAreDisabled()) {
			m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.BACK;
			RobotState.m_intakeDirection = IntakeDirection.BACK;
		} else {
			requestIntakeOff();
		}
	}

	public void requestBackIntakeOut() {
		if(!intakesAreDisabled()) {
			m_intakeBackMotor.set(-IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.BACK;
			RobotState.m_intakeDirection = IntakeDirection.BACK;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeOff() {
		// We don't need to check if the intakes are disabled here, but we
		// use the request prefix for consistency (feel free to change this).
		m_intakeFrontMotor.set(0);
	}

	public void requestFrontIntakeOffBackIntakeIn() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(0);
			m_intakeBackMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.BACK;
			RobotState.m_intakeDirection = IntakeDirection.BACK;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeOffBackIntakeOut() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(0);
			m_intakeBackMotor.set(-IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.BACK;
			RobotState.m_intakeDirection = IntakeDirection.BACK;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeIn() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.FRONT;
			RobotState.m_intakeDirection = IntakeDirection.FRONT;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeOut() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(-IntakeConstants.MAX_INTAKE_SPEED);
			m_lastMotor = IntakeLastMotor.FRONT;
			RobotState.m_intakeDirection = IntakeDirection.FRONT;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeInBackIntakeOff() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
			m_intakeBackMotor.set(0);
			m_lastMotor = IntakeLastMotor.FRONT;
		} else {
			requestIntakeOff();
		}
	}

	public void requestFrontIntakeOutBackIntakeOff() {
		if(!intakesAreDisabled()) {
			m_intakeFrontMotor.set(IntakeConstants.MAX_INTAKE_SPEED);
			m_intakeBackMotor.set(0);
			m_lastMotor = IntakeLastMotor.FRONT;
		} else {
			requestIntakeOff();
		}
	}

	public IntakeLastMotor getLastMotor() {
		return m_lastMotor;
	}

	public void requestIntakeOff() {
		m_intakeMotorGroup.set(0);
		RobotState.m_intakeDirection = IntakeDirection.NONE;
	}

	public void intakeIn() {
		m_intakeMotorGroup.set(1);
		m_lastMotor = IntakeLastMotor.BOTH;
		RobotState.m_intakeDirection = IntakeDirection.BOTH;
	}

	@Config.NumberSlider(min = -1, max = 1, tabName = "Intake")
	public void setIntake(double speed) {
		m_intakeMotorGroup.set(speed);
	}

	public double getCurrentDraw() {
		return m_intakeBackMotor.getOutputCurrent() + m_intakeFrontMotor.getOutputCurrent();
	}

	public boolean backMotorOn() {
		return (m_intakeBackMotor.get() != 0);
	}

	public boolean FrontMotorOn() {
		return (m_intakeFrontMotor.get() != 0);
	}
	@Log.BooleanBox(colorWhenFalse = "#00FF00", colorWhenTrue = "#FF0000", columnIndex = 2, rowIndex = 0, height = 2, tabName = "Intake")
	public boolean intakesAreDisabled() {
		return RobotState.getintakeDirection() == RobotState.IntakeDirection.DISABLED;
	}
}
