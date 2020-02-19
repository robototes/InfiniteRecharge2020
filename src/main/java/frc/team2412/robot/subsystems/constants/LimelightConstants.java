package frc.team2412.robot.subsystems.constants;

import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.DistanceUnits;
import com.robototes.units.UnitTypes.RotationUnits;

public class LimelightConstants {
	// When the lift is up, how high is the center of the limelight?
	public static final Distance LIFT_UP_HEIGHT = new Distance(45, DistanceUnits.INCH);

	// How tall is the center of the target?
	public static final Distance TARGET_CENTER_HEIGHT = new Distance(98.25, DistanceUnits.INCH);

	// What is the angle that the limelight is mounted at?
	public static final Rotations LIMELIGHT_MOUNT_ANGLE = new Rotations(0, RotationUnits.DEGREE);

	public static final Distance INNER_TARGET_DISTANCE = new Distance(29.25, DistanceUnits.INCH);
}
