package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.IntakeConstants.IntakeState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeUpDownSubsystem extends SubsystemBase implements Loggable {

	@Log
	private DoubleSolenoid m_frontIntakeUpDown;
	@Log
	private DoubleSolenoid m_backIntakeUpDown;
	@Log
	private IntakeState m_currentState = IntakeState.WITHDRAWN;

	private Compressor m_compressor;

	public IntakeUpDownSubsystem(DoubleSolenoid frontIntakeUpDown, DoubleSolenoid backIntakeUpDown,
			Compressor compressor) {
		this.m_frontIntakeUpDown = frontIntakeUpDown;
		this.m_backIntakeUpDown = backIntakeUpDown;
		this.m_compressor = compressor;
	}

	public IntakeState getCurrentState() {
		return m_currentState;
	}

	public void frontIntakeUp() {
		setLift(IntakeState.WITHDRAWN, m_frontIntakeUpDown);
	}

	public void frontIntakeDown() {
		setLift(IntakeState.EXTENDED, m_frontIntakeUpDown);
	}

	public void backIntakeUp() {
		setLift(IntakeState.WITHDRAWN, m_backIntakeUpDown);
	}

	public void backIntakeDown() {
		setLift(IntakeState.EXTENDED, m_backIntakeUpDown);
	}

	@Config
	private void setLift(IntakeState newState, DoubleSolenoid doubleSolenoid) {
		m_frontIntakeUpDown.set(newState.value);
		m_currentState = newState;
	}

	@Config
	public void setCompressorStart() {
		m_compressor.start();
	}

}
