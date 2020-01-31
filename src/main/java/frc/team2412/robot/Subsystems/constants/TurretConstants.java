package frc.team2412.robot.Subsystems.constants;

import edu.wpi.first.wpilibj.controller.PIDController;

public class TurretConstants {
	public static final double TICKS_PER_REVOLUTION = 4096d;
	public static final double TICKS_PER_DEGREE = TICKS_PER_REVOLUTION / 360d;

	public static final double PID_P_VALUE = 0.003;
	public static final double PID_D_VALUE = 0.0;

	public static final PIDController TURRET_PID_CONTROLLER = new PIDController(PID_P_VALUE, 0, PID_D_VALUE);

	public static final int TURRET_MAX_TOLERANCE = 10;

	public static final int ENCODER_MAX_ERROR_JUMP = 3800;

	public static final int ENCODER_MIN_SMALL_JUMP = 500;
}
