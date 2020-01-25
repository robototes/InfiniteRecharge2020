package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;
	public static int indexBackMotorID = 1;
	public static int indexFrontMotorID = 2;
	public static int indexMidMotorID = 3;
	public static int BBSensorID = 1;//Back Back
	public static int BTSensorID = 2;//Back Tower
	public static int TSensorID = 3; //Middle Position / Tower
	public static int FTSensorID = 4;//Front Tower
	public static int FFSensorID = 5;//Front Front
	public static int frontDoubleSoulenoidDown_ID = 1;
	public static int frontDoubleSoulenoidUp_ID = 2;
	public static int rearDoubleSoulenoidDown_ID = 3;
	public static int rearDoubleSoulenoidUp_ID = 4;
	
	//motors
	public static CANSparkMax indexFrontMotor = new CANSparkMax(indexFrontMotorID, MotorType.kBrushless);
	public static CANSparkMax indexBackMotor = new CANSparkMax(indexBackMotorID, MotorType.kBrushless);
	public static CANSparkMax indexMidMotor = new CANSparkMax(indexMidMotorID, MotorType.kBrushless);
	
	public static DoubleSolenoid frontClutch = new DoubleSolenoid(frontDoubleSoulenoidUp_ID, frontDoubleSoulenoidDown_ID);
	public static DoubleSolenoid rearClutch = new DoubleSolenoid(rearDoubleSoulenoidUp_ID, rearDoubleSoulenoidDown_ID);
	
	//sensors
	public static DigitalInput BB = new DigitalInput(BBSensorID);
	public static DigitalInput BT = new DigitalInput(BTSensorID);
	public static DigitalInput T = new DigitalInput(TSensorID);
	public static DigitalInput FT = new DigitalInput(FTSensorID);
	public static DigitalInput FF = new DigitalInput(FFSensorID);
	
	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
}
