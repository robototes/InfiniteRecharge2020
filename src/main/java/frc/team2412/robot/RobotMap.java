package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class
public class RobotMap {

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------
	// DriveBase Motor ports
	public static final int DRIVE_LEFT_FRONT_ID = 4;
	public static final int DRIVE_LEFT_BACK_ID = 3;
	public static final int DRIVE_RIGHT_FRONT_ID = 2;
	public static final int DRIVE_RIGHT_BACK_ID = 1;

	private static final int DRIVE_SOLENOID_PORT = 0;

	// DriveBase Motors
	public static WPI_TalonFX driveLeftFront = new WPI_TalonFX(DRIVE_LEFT_FRONT_ID);
	public static WPI_TalonFX driveLeftBack = new WPI_TalonFX(DRIVE_LEFT_BACK_ID);
	public static WPI_TalonFX driveRightFront = new WPI_TalonFX(DRIVE_RIGHT_FRONT_ID);
	public static WPI_TalonFX driveRightBack = new WPI_TalonFX(DRIVE_RIGHT_BACK_ID);

	// DriveBase Gyro
	public static ADXRS450_Gyro driveGyro = new ADXRS450_Gyro();

	// DriveBase Solenoid
	public static Solenoid driveSolenoid = new Solenoid(DRIVE_SOLENOID_PORT);

	// climb mode
	static boolean climbMode = false;

	// climb Pneumatics
	private static final int CLIMB_LEFT_SOLENOID_PORT = 1;
	private static final int CLIMB_RIGHT_SOLENOID_PORT = 2;
	public static Solenoid climbLeftPneumatic = new Solenoid(CLIMB_LEFT_SOLENOID_PORT);
	public static Solenoid climbRightPneumatic = new Solenoid(CLIMB_RIGHT_SOLENOID_PORT);

	// Motors
	private static final int climbMotor1 = 1;
	private static final int climbMotor2 = 2;
	public static CANSparkMax leftClimbMotor = new CANSparkMax(climbMotor1, MotorType.kBrushless);
	public static CANSparkMax rightClimbMotor = new CANSparkMax(climbMotor2, MotorType.kBrushless);

	// INDEX SUBSYSTEM
	// ---------------------------------------------------------------------------
	// IDs
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

	// motors
	public static CANSparkMax indexFrontMotor = new CANSparkMax(indexFrontMotorID, MotorType.kBrushless);
	public static CANSparkMax indexBackMotor = new CANSparkMax(indexBackMotorID, MotorType.kBrushless);
	public static CANSparkMax indexMidMotor = new CANSparkMax(indexMidMotorID, MotorType.kBrushless);

	// sensors
	public static DigitalInput back = new DigitalInput(backSensorID);
	public static DigitalInput backMid = new DigitalInput(backMidSensorID);
	public static DigitalInput mid = new DigitalInput(midSensorID);
	public static DigitalInput frontMid = new DigitalInput(frontMidSensorID);
	public static DigitalInput front = new DigitalInput(frontSensorID);

	// INDEXER CONTROLS THESE NOT INTAKE FYI
	public static DigitalInput intakeFront = new DigitalInput(intakeFrontSensorID);
	public static DigitalInput intakeBack = new DigitalInput(intakeBackSensorID);

	// Turret Subsystem
	// ------------------------------------------------------------------------------
	public static final int turretMotorID = 1;
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(turretMotorID);

	// Flywheel subsystem
	// ------------------------------------------------------------------------------
	public static final int flywheelLeftMotorID = 0;
	public static final int flywheelRightMotorID = 2;

	public static CANSparkMax flywheelLeftMotor = new CANSparkMax(flywheelLeftMotorID, MotorType.kBrushless);
	public static CANSparkMax flywheelRightMotor = new CANSparkMax(flywheelRightMotorID, MotorType.kBrushless);

	// Hood Subsystem
	// -----------------------------------------------------------------------------
	public static final int HOOD_SERVO_PORT = 1;
	public static Servo hoodServo = new Servo(HOOD_SERVO_PORT);

	// INTAKE SUBSYSTEM

	// Intake Solenoid Ports
	public static final int INTAKE_FRONT_LIFT_PORT = 3;
	public static final int INTAKE_BACK_LIFT_PORT = 4;

	public static Solenoid frontIntakeliftSolenoid = new Solenoid(INTAKE_FRONT_LIFT_PORT);
	public static Solenoid backIntakeLiftSolenoid = new Solenoid(INTAKE_BACK_LIFT_PORT);

	public static final int INTAKE_FRONT_MOTOR_PORT = 1;
	public static final int INTAKE_BACK_MOTOR_PORT = 2;

	public static CANSparkMax intakeFrontMotor = new CANSparkMax(INTAKE_FRONT_MOTOR_PORT, MotorType.kBrushless);
	public static CANSparkMax intakeBackMotor = new CANSparkMax(INTAKE_BACK_MOTOR_PORT, MotorType.kBrushless);

	// LIFT SUBSYSTEM
	// -------------------------------------------------------------------------------
	// Lift Solenoid Ports
	public static final int LIFT_DOWN_PORT = 5;
	public static final int LIFT_UP_PORT = 6;

	public static DoubleSolenoid liftUpDown = new DoubleSolenoid(LIFT_DOWN_PORT, LIFT_UP_PORT);

	// CONTROL PANEL SUBSYSTEM
	// ----------------------------------------------------------------------
	// Control Panel I2C
	public static I2C.Port COLOR_SESNOR_PORT = I2C.Port.kOnboard;

	public static ColorSensorV3 colorSensor = new ColorSensorV3(COLOR_SESNOR_PORT);
	public static ColorMatch colorMatcher = new ColorMatch();

	public static final int CONTROL_PANEL_MOTOR_PORT = 1;
	public static WPI_TalonFX colorSensorMotor = new WPI_TalonFX(CONTROL_PANEL_MOTOR_PORT);

	// Limelight subsystem
	// ----------------------------------------------------------------------------------------------
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.create().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.ZERO, StreamMode.STANDARD, SnapshotMode.OFF);

	// Unknown RN
	// -------------------------------------------------------------------------------------------------
	// Compressor
	public static Compressor compressor = new Compressor();

	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(m_robotContainer);
}
