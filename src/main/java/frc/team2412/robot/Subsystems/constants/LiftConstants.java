package frc.team2412.robot.Subsystems.constants;

public class LiftConstants {

	public static enum LiftState {

		// Creates a value called up which is equal to kForward
		UP(true),

		// Creates a value called down which is equal to kReverse
		DOWN(false);

		// Creates a value that can be set to up and down
		public boolean value;

		private LiftState(boolean value) {
			this.value = value;
		}
	}

}
