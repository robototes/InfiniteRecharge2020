package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbState;

public class ClimbLiftSubsystem extends SubsystemBase {

	// For Pneumatics
	private Solenoid leftSolenoid;
	private Solenoid rightSolenoid;

	public ClimbLiftSubsystem(Solenoid leftSolenoid, Solenoid rightSolenoid) {
		this.leftSolenoid = leftSolenoid;
		this.rightSolenoid = rightSolenoid;
	}

	public void deployRails() {
		setClimb(ClimbState.UP);
	}

	public void retractRails() {
		setClimb(ClimbState.DOWN);
	}

	private void setClimb(ClimbState newState) {
		leftSolenoid.set(newState.value);
		rightSolenoid.set(newState.value);
	}
}
