package frc.team2412.robot.subsystems.constants;

import frc.team2412.robot.RobotState;

public class IndexerConstants {
	// NOT CONSTANTS BUT UNIVERSAL VALUES
	
	//
	public enum UnbalancedSide {
		FRONT, BACK;
		public void flip() {
			UnbalancedSide s = this;
			switch (s) {
			case FRONT:
				s = UnbalancedSide.BACK;
				break;
			case BACK:
				s = UnbalancedSide.FRONT;
				break;
			}
		}
	}

	// PID FOR AFTER A SENSOR PICKS UP A BALL
	public static final double PID_P = 0.01;
	public static final double PID_I = 0;
	public static final double PID_D = 0;

	// RATIO
	// GEARBOX = 5:1, SPINDLE DIA = ???
	// STOP 1 INCH AFTER SENSOR SIGNAL
	private static final double SPINDLE_DIAMETER = 1.0;
	public static final double STOP_DISTANCE = 5 * SPINDLE_DIAMETER * Math.PI;
}
