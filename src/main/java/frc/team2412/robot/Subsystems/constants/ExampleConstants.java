package frc.team2412.robot.Subsystems.constants;

import com.robototes.units.Distance;
import com.robototes.units.UnitTypes.DistanceUnits;

// Constants classes will be used to store constants for individual subsystems. This is to reduce clutter in Robot map. No hardware is to be created here
public class ExampleConstants {
	public enum ExampleEnum {
		LOW, MID, HIGH
	}

	// Example constant
	public static final double MAX_SPEED = 1;

	// Testing library implementation works
	public static final Distance EXAMPLE_DISTANCE = new Distance(1, DistanceUnits.INCH);

	// Enums will go below here

}
