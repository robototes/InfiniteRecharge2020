package frc.team2412.robot.Subsystems.constants;

import edu.wpi.first.wpilibj.controller.PIDController;

public class IndexerConstants {
	//NOT CONSTANTS BUT UNIVERSAL VALUES
	public static int numBalls = 0;
	public static UnbalancedSide unbalancedSide;

	//
	public enum UnbalancedSide{
		FRONT, BACK;
		public void flip(){
			switch (this){
				case FRONT:
					unbalancedSide = UnbalancedSide.BACK;
					break;
				case BACK:
					unbalancedSide = UnbalancedSide.FRONT;
					break;
			}
		}
	}

	public enum CommandType{
		INTAKE1, INTAKE2, SWITCH2, INTAKE3, INTAKE4, SWITCH4, INTAKE5;
	}

	//PID FOR AFTER A SENSOR PICKS UP A BALL
	public static final double PID_P = 1;
	public static final double PID_I = 0;
	public static final double PID_D = 1;

	public static final PIDController INDEXER_FRONT_PID_CONTROLLER = new PIDController(PID_P, PID_I, PID_D);
	public static final PIDController INDEXER_BACK_PID_CONTROLLER = new PIDController(PID_P, PID_I, PID_D);

	//RATIO
	//GEARBOX = 5:1, SPINDLE DIA = ???
	//STOP 1 INCH AFTER SENSOR SIGNAL
	private static final double SPINDLE_DIAMETER = 1.0;
	public static final double STOP_DISTANCE = 5*SPINDLE_DIAMETER*Math.PI;
}
