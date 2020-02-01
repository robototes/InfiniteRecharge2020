package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IntakeConstants.IntakeState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeUpDownSubsystem extends SubsystemBase implements Loggable {

	@Log
	private DoubleSolenoid m_intakeUpDown;

	private IntakeState m_currentState = IntakeState.WITHDRAWN;

	public IntakeUpDownSubsystem(DoubleSolenoid intakeUpDown) {
		this.m_intakeUpDown = intakeUpDown;
	}

	public IntakeState getCurrentState() {
		return m_currentState;
	}

	public void intakeDown() {
		setLift(IntakeState.EXTENDED);
	}

	public void intakeUp() {
		setLift(IntakeState.WITHDRAWN);

	}

	@Config
	private void setLift(IntakeState newState) {
		m_intakeUpDown.set(newState.value);
		m_currentState = newState;
	}

}
