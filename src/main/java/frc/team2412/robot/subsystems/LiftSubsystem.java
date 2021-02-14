package frc.team2412.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.LiftConstants.LiftState;

public class LiftSubsystem extends SubsystemBase {

	private DoubleSolenoid liftDoubleSolenoid;

	public LiftSubsystem(DoubleSolenoid liftDoubleSolenoid) {
		this.liftDoubleSolenoid = liftDoubleSolenoid;
	}

	public void liftDown() {
		setLift(LiftState.DOWN);
	}

	public void liftUp() {
		setLift(LiftState.UP);
	}

	private void setLift(LiftState value) {
		liftDoubleSolenoid.set(value.value);
	}

	public LiftState getLiftState() {
		return liftDoubleSolenoid.get() == LiftState.UP.value ? LiftState.UP : LiftState.DOWN;
	}

}
