package frc.team2412.robot;

import infiniteRecharge2020.WPI_TalonSRX;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	//Pneumatics for putting arm into position
	public static int deployArmPneumatic1 = 1;
	public static int deployArmPneumatic2 = 2;
	//Motors
	//public static double climbMotor1 = 1;
	//public static double climbMotor2 = 2;
	private static Object climbMoterID1;
	public static WPI_TalonSRX climbMoter1 = new WPI_TalonSRX(climbMoterID1);
	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
	public static WPI_TalonSRX climbMotor1;
}
