package frc.team2412.robot;

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

	// IDs
	public static final int exampleID = 1;

	// Turret Subsystem
	public static final int turretMotorID = 1;
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(turretMotorID);

	// Flywheel subsystem
	public static final int flywheelMotorID1 = 0;
	public static final int flywheelMotorID2 = 2;

	public static CANSparkMax flywheelMotor1 = new CANSparkMax(flywheelMotorID1, MotorType.kBrushless);
	public static CANSparkMax flywheelMotor2 = new CANSparkMax(flywheelMotorID2, MotorType.kBrushless);

	public static SpeedControllerGroup flywheelSpeedGroup = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

	// Hood Subsystem
	public static final int HOOD_SERVO_PORT = 1;
	public static Servo hoodServo = new Servo(HOOD_SERVO_PORT);

	// Intake DoubleSolenoid Ports
	public static final int INTAKE_UP_PORT = 1;
	public static final int INTAKE_DOWN_PORT = 1;

	public static DoubleSolenoid intakeUpDown = new DoubleSolenoid(INTAKE_UP_PORT, INTAKE_DOWN_PORT);

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

	// ----------------------------------------------------------------------------------------------
	// Limelight subsystem
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.create().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.ZERO, StreamMode.STANDARD, SnapshotMode.OFF);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(robotContainer);
}
