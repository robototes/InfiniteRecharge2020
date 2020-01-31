package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.LiftConstants.LiftState;

public class LiftSubsystem extends SubsystemBase {

	// creates a DoubleSolenoid to use, mainly for ease
	private DoubleSolenoid m_liftUpDown;

	// Sets the state of the lift to down because of init it is down
	private LiftState m_currentState = LiftState.DOWN;

	// For when we create a liftSubsystem, it makes a liftSubsystem which uses our
	// local liftSubsystem
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

	// Takes the passed in LiftState and set the motor to that value. Also changes
	// the current state to that state
	private void setLift(LiftState newState) {
		m_liftUpDown.set(newState.value);
		m_currentState = newState;
	}

	// gets the current state
	public LiftState getCurrentState() {
		return m_currentState;
	}

}
