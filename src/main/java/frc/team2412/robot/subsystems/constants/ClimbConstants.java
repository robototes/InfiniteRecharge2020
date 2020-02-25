package frc.team2412.robot.subsystems.constants;

import com.robototes.units.Distance;
import com.robototes.units.InterUnitRatio;
import com.robototes.units.UnitTypes.DistanceUnits;
import com.robototes.units.UnitTypes.RotationUnits;

public class ClimbConstants {

	public static enum ClimbHeight {
		BOTTOM(new Distance(56, DistanceUnits.INCH)), MIDDLE(new Distance(64, DistanceUnits.INCH)),
		TOP(new Distance(72, DistanceUnits.INCH));

		public Distance value;

		private ClimbHeight(Distance inch) {
			this.value = inch;
		}

	}

	public static enum ClimbState {

		/** Creates a value called down which is equal to kReverse */
		DOWN(false),

		/** Creates a value called up which is equal to kForward */
		UP(true);

		/** Creates a value that can be set to up and down */
		public boolean value;

		private ClimbState(boolean value) {
			this.value = value;
		}
	}

	public static final Distance CLIMB_OFFSET_HEIGHT = new Distance(0);

	public static final double DEADBAND = 1;

	public static final int MAX_SPEED = 1;

	public static final InterUnitRatio<RotationUnits, DistanceUnits> MOTOR_REVOLUTIONS_TO_INCHES = new InterUnitRatio<RotationUnits, DistanceUnits>(
			RotationUnits.ROTATION, 1, DistanceUnits.INCH);

}