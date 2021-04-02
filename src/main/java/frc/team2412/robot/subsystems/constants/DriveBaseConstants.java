package frc.team2412.robot.subsystems.constants;

import edu.wpi.first.wpilibj.util.Units;

public class DriveBaseConstants {

	public static final double wheelRadiusMeters = Units.inchesToMeters(3);
	public static final double metersPerWheelRevolution = wheelRadiusMeters * 2 * Math.PI;

	public static final double encoderTicksPerRevolution = 2048.0;

	public static final int ENCODER_TICKS_PER_SECOND = 10;

	public static final double highGearRatio = 1 / -8.16;
	public static final double lowGearRatio = 1 / -17.65;

	public static final double brownoutFactor = 3;

}
