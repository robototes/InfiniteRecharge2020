package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.ClimbConstants.ClimbState;
import io.github.oblarg.oblog.annotations.Config;

public class ClimbLiftSubsystem extends SubsystemBase {

	// For Pneumatics
	private DoubleSolenoid m_leftPneumatic;
	private DoubleSolenoid m_rightPneumatic;

	public ClimbLiftSubsystem(DoubleSolenoid leftPneumatic, DoubleSolenoid rightPneumatic) {

		m_leftPneumatic = leftPneumatic;
		m_rightPneumatic = rightPneumatic;

	}

	public void deployRails() {
		setClimb(ClimbState.UP);
	}

	public void retractRails() {
		setClimb(ClimbState.DOWN);
	}

	@Config
	private void setClimb(ClimbState newState) {
		m_leftPneumatic.set(newState.value);
		m_rightPneumatic.set(newState.value);
	}
}
