package frc.team2412.robot.subsystems.constants;

public class IntakeConstants {

	public enum IntakeDirection {
		NONE, FRONT, BACK, BOTH;

		@Override
		public String toString() {
			if (this.equals(FRONT)) {
				return "Front";
			} else if (this.equals(BACK)) {
				return "Back";
			} else if (this.equals(BOTH)) {
				return "Both";
			} else {
				return "None";
			}
		}
	}

	public static enum IntakeLastMotor {
		FRONT(1), BOTH(0), BACK(-1);

		public double value;

		private IntakeLastMotor(double value) {
			this.value = value;
		}
	}

	public static enum IntakeState {

		// Creates a value called up which is equal to kForward
		EXTENDED(true),

		// Creates a value called down which is equal to kReverse
		WITHDRAWN(false);

		// Creates a value that can be set to up and down
		public boolean value;

		private IntakeState(boolean value) {
			this.value = value;
		}
	}

	public static final double MAX_INTAKE_SPEED = 0.75;

}
