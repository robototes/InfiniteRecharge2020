package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class ClimbLiftSubsystem extends SubsystemBase implements Loggable {

	// For Pneumatics
	@Log(tabName = "Climb")
	private Solenoid m_leftPneumatic;
	@Log(tabName = "Climb")
	private Solenoid m_rightPneumatic;

	public ClimbLiftSubsystem(Solenoid leftPneumatic, Solenoid rightPneumatic) {
		m_leftPneumatic = leftPneumatic;
		m_rightPneumatic = rightPneumatic;
	}

	public void deployRails() {
		setClimb(ClimbState.UP);
	}

	public void retractRails() {
		setClimb(ClimbState.DOWN);
	}

	private void setClimb(ClimbState newState) {
		m_leftPneumatic.set(newState.value);
		m_rightPneumatic.set(newState.value);
	}
}
