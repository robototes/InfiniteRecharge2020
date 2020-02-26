package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeUpDownSubsystem extends SubsystemBase implements Loggable {

	@Log(tabName = "Intake")
	private Solenoid m_frontIntakeUpDown;
	@Log(tabName = "Intake")
	private Solenoid m_backIntakeUpDown;
	@Log.ToString(tabName = "Intake")
	private IntakeState m_currentState = IntakeState.WITHDRAWN;

	public IntakeUpDownSubsystem(Solenoid frontIntakeUpDown, Solenoid backIntakeUpDown) {
		this.m_frontIntakeUpDown = frontIntakeUpDown;
		this.m_backIntakeUpDown = backIntakeUpDown;
	}

	public IntakeState getCurrentState() {
		return m_currentState;
	}

	public void frontIntakeUp() {
		setIntake(IntakeState.WITHDRAWN, m_frontIntakeUpDown);
	}

	public void frontIntakeDown() {
		setIntake(IntakeState.EXTENDED, m_frontIntakeUpDown);
	}

	public void backIntakeUp() {
		setIntake(IntakeState.WITHDRAWN, m_backIntakeUpDown);
	}

	public void backIntakeDown() {
		setIntake(IntakeState.EXTENDED, m_backIntakeUpDown);
	}

	public boolean isFrontIntakeUp() {
		return m_frontIntakeUpDown.get();
	}

	public boolean isBackIntakeUp() {
		return m_backIntakeUpDown.get();
	}

	private void setIntake(IntakeState newState, Solenoid Solenoid) {
		m_frontIntakeUpDown.set(newState.value);
		m_currentState = newState;
	}

}
