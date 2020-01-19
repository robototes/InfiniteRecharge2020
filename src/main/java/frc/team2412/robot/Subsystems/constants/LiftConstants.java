package frc.team2412.robot.Subsystems.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class LiftConstants {

	public static enum LiftState {
		UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse);

		public DoubleSolenoid.Value value;

		private LiftState(DoubleSolenoid.Value value) {
			this.value = value;
		}
	}

}
