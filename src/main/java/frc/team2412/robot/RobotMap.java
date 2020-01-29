package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	
	//climb mode
	static boolean climbMode = false;
	
	//climb Pneumatics
	public static int pneumatic1 = 1;
	public static Solenoid leftPneumatic = new Solenoid(pneumatic1);
	public static int pneumatic2 = 2;
	public static Solenoid rightPneumatic = new Solenoid(pneumatic2);
	
	//Motors
	public static int climbMotor1 = 1;
	public static WPI_TalonSRX leftClimbMotor = new WPI_TalonSRX(climbMotor1);
	public static int climbMotor2 = 2;
	public static WPI_TalonSRX rightClimbMotor = new WPI_TalonSRX(climbMotor2);
	
	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
}
