package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbState;

public class ClimbLiftSubsystem extends SubsystemBase {

	// For Pneumatics
	private Solenoid m_leftSolenoid;
	private Solenoid m_rightSolenoid;

	public ClimbLiftSubsystem(Solenoid leftSolenoid, Solenoid rightSolenoid) {
		m_leftSolenoid = leftSolenoid;
		m_rightSolenoid = rightSolenoid;
	}

	public void deployRails() {
		setClimb(ClimbState.UP);
	}

	public void retractRails() {
		setClimb(ClimbState.DOWN);
	}

	private void setClimb(ClimbState newState) {
		m_leftSolenoid.set(newState.value);
		m_rightSolenoid.set(newState.value);
	}
}
