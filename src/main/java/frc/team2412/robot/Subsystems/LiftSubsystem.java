package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.Subsystems.constants.LiftConstants.LiftState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class LiftSubsystem extends SubsystemBase implements Loggable {

	// creates a Solenoid to use, mainly for ease
	@Log
	private Solenoid m_liftUpDown;

	private Compressor m_compressor;

	// Sets the state of the lift to down because of init it is down
	@Log
	private LiftState m_currentState = LiftState.DOWN;

	// For when we create a liftSubsystem, it makes a liftSubsystem which uses our
	// local liftSubsystem
	public LiftSubsystem(Solenoid liftUpDown, Compressor compressor) {
		this.m_liftUpDown = liftUpDown;
		this.m_compressor = compressor;
		this.setName("Lift Subsystem");
	}

	// gets the current state
	public LiftState getCurrentState() {
		return m_currentState;
	}

	public void liftDown() {
		setLift(LiftState.DOWN);
		RobotState.m_liftSolenoidState = RobotState.LiftState.WITHDRAWN;
	}

	public void liftUp() {
		setLift(LiftState.UP);
		RobotState.m_liftSolenoidState = RobotState.LiftState.EXTENDED;
	}

	@Config
	public void setCompressorStart() {
		m_compressor.start();
	}

	// Takes the passed in LiftState and set the motor to that value. Also changes
	// the current state to that state
	@Config
	private void setLift(LiftState value) {
		m_liftUpDown.set(value.value);
	}

}
