package frc.team2412.robot.Subsystems.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeConstants {

	public static enum IntakeLastMotor {
		FRONT(1), BOTH(0), BACK(-1);

		public double value;

		private IntakeLastMotor(double value) {
			this.value = value;
		}
	}

	public static enum IntakeState {

		// Creates a value called up which is equal to kForward
		EXTENDED(DoubleSolenoid.Value.kForward),

		// Creates a value called down which is equal to kReverse
		WITHDRAWN(DoubleSolenoid.Value.kReverse);

		// Creates a value that can be set to up and down
		public DoubleSolenoid.Value value;

		private IntakeState(DoubleSolenoid.Value value) {
			this.value = value;
		}
	}

	public static final double MAX_INTAKE_SPEED = 1;

}
