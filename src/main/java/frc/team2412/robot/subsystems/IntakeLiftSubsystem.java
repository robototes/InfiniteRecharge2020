package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeState;

public class IntakeLiftSubsystem extends SubsystemBase {

	private Solenoid frontSolenoid;
	private Solenoid backSolenoid;

	private IntakeState currentState = IntakeState.WITHDRAWN;

	public IntakeLiftSubsystem(Solenoid frontSolenoid, Solenoid backSolenoid) {
		this.frontSolenoid = frontSolenoid;
		this.backSolenoid = backSolenoid;
	}

	public IntakeState getCurrentState() {
		return currentState;
	}

	public void frontIntakeUp() {
		setIntake(IntakeState.WITHDRAWN, frontSolenoid);
	}

	public void frontIntakeDown() {
		setIntake(IntakeState.EXTENDED, frontSolenoid);
	}

	public void backIntakeUp() {
		setIntake(IntakeState.WITHDRAWN, backSolenoid);
	}

	public void backIntakeDown() {
		setIntake(IntakeState.EXTENDED, backSolenoid);
	}

	public boolean isFrontIntakeUp() {
		return frontSolenoid.get();
	}

	public boolean isBackIntakeUp() {
		return backSolenoid.get();
	}

	private void setIntake(IntakeState newState, Solenoid solenoid) {
		solenoid.set(newState.value);
		currentState = newState;
	}

}
