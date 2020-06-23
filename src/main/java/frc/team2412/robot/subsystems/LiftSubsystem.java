package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.LiftConstants.LiftState;

public class LiftSubsystem extends SubsystemBase{

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
