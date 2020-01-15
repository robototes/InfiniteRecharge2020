package frc.team2412.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;
	
	//Button ports
	public static int LIFT_UP_BUTTON_PORT = 1;
	public static int LIFT_DOWN_BUTTON_PORT = 1;
	
	public static boolean LiftIsUp = false;

	//DoubleSolenoid Ports
	public static int LIFT_UP_PORT = 1;
	public static int LIFT_DOWN_PORT = 1;

	public static DoubleSolenoid liftUpDown = new DoubleSolenoid(LIFT_UP_PORT, LIFT_DOWN_PORT);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
}
