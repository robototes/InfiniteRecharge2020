package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;

	// Limelight subsystem
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.FOUR, StreamMode.STANDARD, SnapshotMode.OFF);

	// Turret Subsystem
	public static int turretMotorID = 1;
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(turretMotorID);

	// Flywheel subsystem
//	public static int flywheelMotorID1 = 0;
//	public static int flywheelMotorID2 = 2;
//
//	public static CANSparkMax flywheelMotor1 = new CANSparkMax(flywheelMotorID1, MotorType.kBrushless);
//	public static CANSparkMax flywheelMotor2 = new CANSparkMax(flywheelMotorID2, MotorType.kBrushless);
//
//	public static SpeedControllerGroup flywheelSpeedGroup = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

	// Hood Subsystem
//	public static final int HOOD_SERVO_PORT = 1;
//	public static Servo hoodServo = new Servo(HOOD_SERVO_PORT);

	// Drivebase Subsystem

	public static int[] drivebaseIDs = { 3, 9, 6, 8, 2, 5 };

	public static SpeedControllerGroup leftController = new SpeedControllerGroup(new WPI_TalonSRX(drivebaseIDs[0]),
			new WPI_TalonSRX(drivebaseIDs[2]), new WPI_TalonSRX(drivebaseIDs[4]));
	public static SpeedControllerGroup rightController = new SpeedControllerGroup(new WPI_TalonSRX(drivebaseIDs[1]),
			new WPI_TalonSRX(drivebaseIDs[3]), new WPI_TalonSRX(drivebaseIDs[5]));

	public static DifferentialDrive robotDrive = new DifferentialDrive(leftController, rightController);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(robotContainer);
}
