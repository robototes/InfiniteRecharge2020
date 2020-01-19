package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.LiftConstants.LiftState;

public class LiftSubsystem extends SubsystemBase {

	private DoubleSolenoid m_liftUpDown;

	private LiftState m_currentState = LiftState.DOWN;

	public LiftSubsystem(DoubleSolenoid liftUpDown) {
		this.m_liftUpDown = liftUpDown;
		this.setName("Lift Subsystem");
	}

	public void liftUp() {
		setLift(LiftState.UP);
	}

	public void liftDown() {
		setLift(LiftState.DOWN);
	}

	private void setLift(LiftState newState) {
		m_liftUpDown.set(newState.value);
		m_currentState = newState;
	}

	public LiftState getCurrentState() {
		return m_currentState;
	}

}
