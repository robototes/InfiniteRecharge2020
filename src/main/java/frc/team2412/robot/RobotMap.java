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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class
public class RobotMap {

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------
	// DriveBase Motor ports
	public static final int LEFT_FRONT_ID = 0;
	public static final int LEFT_BACK_ID = 0;
	public static final int RIGHT_FRONT_ID = 0;
	public static final int RIGHT_BACK_ID = 0;

	// DriveBase Motors
	public static WPI_TalonFX leftFront = new WPI_TalonFX(LEFT_FRONT_ID);
	public static WPI_TalonFX leftBack = new WPI_TalonFX(LEFT_BACK_ID);
	public static WPI_TalonFX rightFront = new WPI_TalonFX(RIGHT_FRONT_ID);
	public static WPI_TalonFX rightBack = new WPI_TalonFX(RIGHT_BACK_ID);

	// DriveBase SpeedControllerGroups
	public static SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftBack);
	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightBack);

	// DriveBase DifferentialDrive
	public static DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);

	// DriveBase Gyro
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	// climb mode
	static boolean climbMode = false;

	// climb Pneumatics
	private static final int pneumatic1Open = 1;
	private static final int pneumatic1Closed = 2;
	private static final int pneumatic2Open = 3;
	private static final int pneumatic2Closed = 4;
	public static DoubleSolenoid climbLeftPneumatic = new DoubleSolenoid(pneumatic1Open, pneumatic1Closed);
	public static DoubleSolenoid climbRightPneumatic = new DoubleSolenoid(pneumatic2Open, pneumatic2Closed);

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
	private final static int frontDoubleSolenoidDown_ID = 1;
	private final static int frontDoubleSolenoidUp_ID = 2;
	private final static int rearDoubleSolenoidDown_ID = 3;
	private final static int rearDoubleSolenoidUp_ID = 4;

	// motors
	public static CANSparkMax indexFrontMotor = new CANSparkMax(indexFrontMotorID, MotorType.kBrushless);
	public static CANSparkMax indexBackMotor = new CANSparkMax(indexBackMotorID, MotorType.kBrushless);
	public static CANSparkMax indexMidMotor = new CANSparkMax(indexMidMotorID, MotorType.kBrushless);

	public static DoubleSolenoid frontClutch = new DoubleSolenoid(frontDoubleSolenoidUp_ID, frontDoubleSolenoidDown_ID);
	public static DoubleSolenoid rearClutch = new DoubleSolenoid(rearDoubleSolenoidUp_ID, rearDoubleSolenoidDown_ID);

	// sensors
	public static DigitalInput back = new DigitalInput(backSensorID);
	public static DigitalInput backMid = new DigitalInput(backMidSensorID);
	public static DigitalInput mid = new DigitalInput(midSensorID);
	public static DigitalInput frontMid = new DigitalInput(frontMidSensorID);
	public static DigitalInput front = new DigitalInput(frontSensorID);

	// INDEXER CONTROLS THESE NOT INTAKE FYI
	public static DigitalInput intakeFront = new DigitalInput(intakeFrontSensorID);
	public static DigitalInput intakeBack = new DigitalInput(intakeBackSensorID);
	public static final int exampleID = 1;

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

	// Intake DoubleSolenoid Ports
	public static final int INTAKE_FRONT_UP_PORT = 1;
	public static final int INTAKE_FRONT_DOWN_PORT = 1;
	public static final int INTAKE_BACK_UP_PORT = 1;
	public static final int INTAKE_BACK_DOWN_PORT = 1;

	public static DoubleSolenoid frontIntakeUpDown = new DoubleSolenoid(INTAKE_FRONT_UP_PORT, INTAKE_FRONT_DOWN_PORT);
	public static DoubleSolenoid backIntakeUpDown = new DoubleSolenoid(INTAKE_BACK_UP_PORT, INTAKE_BACK_DOWN_PORT);

	public static final int INTAKE_FRONT_PORT = 1;
	public static final int INTAKE_BACK_PORT = 2;

	public static CANSparkMax intakeFrontMotor = new CANSparkMax(INTAKE_FRONT_PORT, MotorType.kBrushless);
	public static CANSparkMax intakeBackMotor = new CANSparkMax(INTAKE_BACK_PORT, MotorType.kBrushless);

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
