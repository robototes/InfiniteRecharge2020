package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	private final static int exampleID = 1;
	private final static int indexBackMotorID = 1;
	private final static int indexFrontMotorID = 2;
	private final static int indexMidMotorID = 3;
	private final static int backSensorID = 1;
	private final static int backMidSensorID = 2;
	private final static int midSensorID = 3; 
	private final static int frontMidSensorID = 4;
	private final static int frontSensorID = 5;
	private final static int intakeFrontSensorID = 6;
	private final static int intakeBackSensorID = 7;
	private final static int frontDoubleSolenoidDown_ID = 1;
	private final static int frontDoubleSolenoidUp_ID = 2;
	private final static int rearDoubleSolenoidDown_ID = 3;
	private final static int rearDoubleSolenoidUp_ID = 4;
	
	//motors
	public static CANSparkMax indexFrontMotor = new CANSparkMax(indexFrontMotorID, MotorType.kBrushless);
	public static CANSparkMax indexBackMotor = new CANSparkMax(indexBackMotorID, MotorType.kBrushless);
	public static CANSparkMax indexMidMotor = new CANSparkMax(indexMidMotorID, MotorType.kBrushless);
	
	public static DoubleSolenoid frontClutch = new DoubleSolenoid(frontDoubleSolenoidUp_ID, frontDoubleSolenoidDown_ID);
	public static DoubleSolenoid rearClutch = new DoubleSolenoid(rearDoubleSolenoidUp_ID, rearDoubleSolenoidDown_ID);
	
	//sensors
	public static DigitalInput back = new DigitalInput(backSensorID);
	public static DigitalInput backMid = new DigitalInput(backMidSensorID);
	public static DigitalInput mid = new DigitalInput(midSensorID);
	public static DigitalInput frontMid = new DigitalInput(frontMidSensorID);
	public static DigitalInput front = new DigitalInput(frontSensorID);
	
	//INDEXER CONTROLS THESE NOT INTAKE FYI
	public static DigitalInput intakeFront = new DigitalInput(intakeFrontSensorID);
	public static DigitalInput intakeBack = new DigitalInput(intakeBackSensorID);
	
	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
}