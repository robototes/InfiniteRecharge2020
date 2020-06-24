package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.constants.LiftConstants.LiftState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class LiftSubsystem extends SubsystemBase implements Loggable {

	// creates a Solenoid to use, mainly for ease
	@Log(tabName = "Lift")
	private DoubleSolenoid m_liftUpDown;

	public LiftSubsystem(DoubleSolenoid liftUpDown) {
		this.m_liftUpDown = liftUpDown;
	}

	public void liftDown() {
		setLift(LiftState.DOWN);
	}

	public void liftUp() {
		setLift(LiftState.UP);
	}

	private void setLift(LiftState value) {
		m_liftUpDown.set(value.value);
		RobotState.m_liftSolenoidState = value;
	}

}
