package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class
public class RobotMap {

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------
	// DriveBase Motor ports

	// Flywheel subsystem
	// ------------------------------------------------------------------------------
	public static final int flywheelLeftMotorID = 5;
	public static final int flywheelRightMotorID = 7;

	public static CANSparkMax flywheelLeftMotor = new CANSparkMax(flywheelLeftMotorID, MotorType.kBrushless);
	public static CANSparkMax flywheelRightMotor = new CANSparkMax(flywheelRightMotorID, MotorType.kBrushless);

	// Hood Subsystem
	// -----------------------------------------------------------------------------
	public static final int HOOD_SERVO_PORT_1 = 0;
	public static final int HOOD_SERVO_PORT_2 = 1;
	public static Servo hoodServo1 = new Servo(HOOD_SERVO_PORT_1);
	public static Servo hoodServo2 = new Servo(HOOD_SERVO_PORT_2);


	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(m_robotContainer);
}
