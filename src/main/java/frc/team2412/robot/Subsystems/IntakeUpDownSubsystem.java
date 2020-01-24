package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IntakeConstants.IntakeState;

public class IntakeUpDownSubsystem extends SubsystemBase {
	
	private DoubleSolenoid m_intakeUpDown;
	
	private IntakeState m_currentState = IntakeState.WITHDRAWN;
	
	public IntakeUpDownSubsystem(DoubleSolenoid intakeUpDown) {
		this.m_intakeUpDown	= intakeUpDown;
	}

	public void intakeUp() {
		setLift(IntakeState.WITHDRAWN);

	}

	public void intakeDown() {
		setLift(IntakeState.EXTENDED);
	}
	
	private void setLift(IntakeState newState) {
		m_intakeUpDown.set(newState.value);
		m_currentState = newState;
	}
	
	public IntakeState getCurrentState() {
		return m_currentState;
	}

}
