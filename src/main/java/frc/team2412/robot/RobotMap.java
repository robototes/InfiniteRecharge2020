package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs

	// climb mode
	static boolean climbMode = false;

	// climb Pneumatics
	public static final int pneumatic1Open = 1;
	public static final int pneumatic1Closed = 2;
	public static DoubleSolenoid leftPneumatic = new DoubleSolenoid(pneumatic1Open, pneumatic1Closed);
	public static final int pneumatic2Open = 3;
	public static final int pneumatic2Closed = 4;
	public static DoubleSolenoid rightPneumatic = new DoubleSolenoid(pneumatic2Open, pneumatic2Closed);

	// Motors
	public static final int climbMotor1 = 1;
	public static WPI_TalonSRX leftClimbMotor = new WPI_TalonSRX(climbMotor1);
	public static final int climbMotor2 = 2;
	public static WPI_TalonSRX rightClimbMotor = new WPI_TalonSRX(climbMotor2);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
	public static OI m_OI = new OI(robotContainer);
}
