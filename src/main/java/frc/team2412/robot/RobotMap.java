package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------
	// DriveBase Motor ports
	public static final int LEFT_FRONT_ID = 0;
	public static final int LEFT_BACK_ID = 0;
	public static final int RIGHT_FRONT_ID = 0;
	public static final int RIGHT_BACK_ID = 0;

	// DriveBase Motors
	private static Talon leftFront = new Talon(LEFT_FRONT_ID);
	private static Talon leftBack = new Talon(LEFT_BACK_ID);
	private static Talon rightFront = new Talon(RIGHT_FRONT_ID);
	private static Talon rightBack = new Talon(RIGHT_BACK_ID);

	// DriveBase SpeedControllerGroups
	public static SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftBack);
	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightBack);

	// DriveBase DifferentialDrive
	public static DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);

	// DriveBase Gyro
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	// INTAKE SUBSYSTEM
	// -----------------------------------------------------------------------------
	// Intake motor ports
	public static final int INTAKE_FRONT_MOTOR_PORT = 1;
	public static final int INTAKE_BACK_MOTOR_PORT = 1;

	// Intake Motors
	public static CANSparkMax intakeFront = new CANSparkMax(INTAKE_FRONT_MOTOR_PORT, MotorType.kBrushless);
	public static CANSparkMax intakeBack = new CANSparkMax(INTAKE_BACK_MOTOR_PORT, MotorType.kBrushless);

	// Intake DoubleSolenoid Ports
	public static final int INTAKE_UP_PORT = 1;
	public static final int INTAKE_DOWN_PORT = 1;

	public static DoubleSolenoid intakeUpDown = new DoubleSolenoid(INTAKE_UP_PORT, INTAKE_DOWN_PORT);

	// LIFT SUBSYSTEM
	// -------------------------------------------------------------------------------
	// Lift DoubleSolenoid Ports
	public static final int LIFT_UP_PORT = 1;
	public static final int LIFT_DOWN_PORT = 1;

	public static DoubleSolenoid liftUpDown = new DoubleSolenoid(LIFT_UP_PORT, LIFT_DOWN_PORT);

	// CONTROL PANEL SUBSYSTEM
	// ----------------------------------------------------------------------
	// Control Panel I2C
	public static I2C.Port COLOR_SESNOR_PORT = I2C.Port.kOnboard;

	public static ColorSensorV3 colorSensor = new ColorSensorV3(COLOR_SESNOR_PORT);
	public static ColorMatch colorMatcher = new ColorMatch();

	public static final int CONTROL_PANEL_MOTOR_PORT = 1;
	public static Talon colorSensorMotor = new Talon(CONTROL_PANEL_MOTOR_PORT);

	// ----------------------------------------------------------------------------------------------
	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();

	public static OI m_OI = new OI(robotContainer);
}
