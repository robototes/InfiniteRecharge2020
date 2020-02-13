package frc.team2412.robot.Subsystems.constants;

import com.robototes.control.Gearbox;
import com.robototes.units.Distance;
import com.robototes.units.InterUnitRatio;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.DistanceUnits;
import com.robototes.units.UnitTypes.RotationUnits;
import com.robototes.units.UnitTypes.TimeUnits;

public class FlywheelConstants {
	
	public static final Distance OUTPUT_WHEEL_DIAMETER = new Distance(4, DistanceUnits.INCH);
	public static final Distance OUTPUT_WHEEL_CIRCUMFRENCE = OUTPUT_WHEEL_DIAMETER.multiply(new Distance(Math.PI));

	public static final InterUnitRatio<RotationUnits, DistanceUnits> FLYWHEEL_ROTATIONS_TO_FLYWHEEL_METERS = new InterUnitRatio<RotationUnits, DistanceUnits>(
			RotationUnits.ROTATION, OUTPUT_WHEEL_CIRCUMFRENCE.distance, DistanceUnits.METER);

	public static final Gearbox NEO_ROTATIONS_TO_FLYWHEEL_ROTATIONS = new Gearbox(new Rotations(0), 2,
			new Rotations(0));

	public static final InterUnitRatio<RotationUnits, RotationUnits> RPM_TO_RPS = new InterUnitRatio<RotationUnits, RotationUnits>(
			RotationUnits.ROTATION, TimeUnits.MINUTE.getRatio(TimeUnits.SECOND).ratio, RotationUnits.ROTATION);

	public static final InterUnitRatio<RotationUnits, RotationUnits> NEO_RPM_TO_FLYWHEEL_ROTATIONS_PER_SECOND = new InterUnitRatio<RotationUnits, RotationUnits>(
			RPM_TO_RPS, NEO_ROTATIONS_TO_FLYWHEEL_ROTATIONS.getRatio());

	public static final InterUnitRatio<RotationUnits, DistanceUnits> NEO_RPM_TO_FLYWHEEL_MPS = new InterUnitRatio<RotationUnits, DistanceUnits>(
			NEO_RPM_TO_FLYWHEEL_ROTATIONS_PER_SECOND, FLYWHEEL_ROTATIONS_TO_FLYWHEEL_METERS);
	
	public static final double I = 0;
	public static final double D = 0;
	public static final double P = 0;
	
	

}
