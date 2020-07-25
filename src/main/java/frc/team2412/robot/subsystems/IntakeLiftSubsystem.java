package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeState;

public class IntakeLiftSubsystem extends SubsystemBase {

	private Solenoid m_frontIntakeUpDown;
	private Solenoid m_backIntakeUpDown;

	private IntakeState m_currentState = IntakeState.WITHDRAWN;

	public IntakeLiftSubsystem(Solenoid frontIntakeUpDown, Solenoid backIntakeUpDown) {
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

	private void setIntake(IntakeState newState, Solenoid solenoid) {
		solenoid.set(newState.value);
		m_currentState = newState;
	}

}
