package frc.team2412.robot.Subsystems.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class LiftConstants {

	public static enum LiftState {

		// Creates a value called up which is equal to kForward
		UP(DoubleSolenoid.Value.kForward),

		// Creates a value called down which is equal to kReverse
		DOWN(DoubleSolenoid.Value.kReverse);

		// Creates a value that can be set to up and down
		public DoubleSolenoid.Value value;

		private LiftState(DoubleSolenoid.Value value) {
			this.value = value;
		}
	}

}
