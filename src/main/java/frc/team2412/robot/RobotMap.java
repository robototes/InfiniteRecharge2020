package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// Drive Motor port
	public static final int LEFT_FRONT_ID = 0;
	public static final int LEFT_BACK_ID = 0;
	public static final int RIGHT_FRONT_ID = 0;
	public static final int RIGHT_BACK_ID = 0;

	// Drive Motors

	private static Talon leftFront = new Talon(LEFT_FRONT_ID);
	private static Talon leftBack = new Talon(LEFT_BACK_ID);
	private static Talon rightFront = new Talon(RIGHT_FRONT_ID);
	private static Talon rightBack = new Talon(RIGHT_BACK_ID);

	public static SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftBack);
	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightBack);

	public static DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	// IDs
	public static final int exampleID = 1;

	// Limelight subsystem
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.FOUR, StreamMode.STANDARD, SnapshotMode.OFF);

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

	// Joystick ports
	public static final int DRIVER_STICK_PORT = 0;
	public static final int CODRIVER_STICK_PORT = 1;

	// Button ports
	public static final int LIFT_UP_BUTTON_PORT = 1;
	public static final int LIFT_DOWN_BUTTON_PORT = 1;

	// DoubleSolenoid Ports
	public static int LIFT_UP_PORT = 1;
	public static int LIFT_DOWN_PORT = 1;

	public static DoubleSolenoid liftUpDown = new DoubleSolenoid(LIFT_UP_PORT, LIFT_DOWN_PORT);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(robotContainer);
}
