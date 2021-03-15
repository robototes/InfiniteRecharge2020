package frc.team2412.robot.subsystems.constants;

public class DriveBaseConstants {

	public static final double wheelDiameterMeters = 0.1524;
	public static final double metersPerWheelRevolution = wheelDiameterMeters * Math.PI;

	public static final double encoderTicksPerRevolution = 2048.0;

	public static final int ENCODER_TICKS_PER_SECOND = 10;

	public static final double highGearRatio = 1 / 7.08;
	public static final double lowGearRatio = 1 / 6.13;

	public static final double brownoutFactor = 3;

}
