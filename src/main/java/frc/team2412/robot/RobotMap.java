package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.team2412.robot.RobotMapConstants.CANBus;
import frc.team2412.robot.RobotMapConstants.DIOPort;
import frc.team2412.robot.RobotMapConstants.IndexIntakeModule;
import frc.team2412.robot.RobotMapConstants.PWMPort;
import frc.team2412.robot.RobotMapConstants.PneumaticPort;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class
public class RobotMap {

	// SET THESE TO CHANGE MODULE
	public static IndexIntakeModule frontIndexIntakeModule = IndexIntakeModule.ONE;
	public static IndexIntakeModule backIndexIntakeModule = IndexIntakeModule.TWO;

	// NOT THIS ONE
	public static IndexIntakeModule spareIndexIntakeModule = IndexIntakeModule.THREE;

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------

	// DriveBase Motors
	public static WPI_TalonFX driveLeftFrontMotor = new WPI_TalonFX(CANBus.DRIVE_LEFT_FRONT.id);
	public static WPI_TalonFX driveLeftBackMotor = new WPI_TalonFX(CANBus.DRIVE_LEFT_BACK.id);
	public static WPI_TalonFX driveRightFrontMotor = new WPI_TalonFX(CANBus.DRIVE_RIGHT_FRONT.id);
	public static WPI_TalonFX driveRightBackMotor = new WPI_TalonFX(CANBus.DRIVE_RIGHT_BACK.id);
	// DriveBase
	public static final Gyro driveGyro = new AHRS();

	// DriveBase Solenoid
	public static Solenoid driveSolenoid = new Solenoid(PneumaticPort.DRIVE.id);

	// climb Pneumatics
	public static Solenoid climbLeftSolenoid = new Solenoid(PneumaticPort.CLIMB_LEFT.id);
	public static Solenoid climbRightSolenoid = new Solenoid(PneumaticPort.CLIMB_RIGHT.id);

	// Motors
	public static CANSparkMax climbLeftMotor = new CANSparkMax(CANBus.CLIMB1.id, MotorType.kBrushless);
	public static CANSparkMax climbRightMotor = new CANSparkMax(CANBus.CLIMB2.id, MotorType.kBrushless);

	// INDEX SUBSYSTEM
	// ---------------------------------------------------------------------------

	// motors
	public static CANSparkMax indexFrontMotor, indexBackMotor;
	public static CANSparkMax indexLeftMidMotor = new CANSparkMax(CANBus.INDEX_LEFT_MID.id, MotorType.kBrushless);

	public static CANSparkMax indexRightMidMotor = new CANSparkMax(CANBus.INDEX_RIGHT_MID.id, MotorType.kBrushless);

	public static CANSparkMax intakeFrontMotor, intakeBackMotor;

	// sensors
	public static DigitalInput indexBackSensor = new DigitalInput(DIOPort.BACK_SENSOR.id);
	public static DigitalInput indexBackMidSensor = new DigitalInput(DIOPort.BACK_MID_SENSOR.id);
	public static DigitalInput indexBackInnerSensor = new DigitalInput(DIOPort.BACK_INNER_SENSOR.id);
	public static DigitalInput indexFrontInnerSensor = new DigitalInput(DIOPort.FRONT_INNER_SENSOR.id);
	public static DigitalInput indexFrontMidSensor = new DigitalInput(DIOPort.FRONT_MID_SENSOR.id);
	public static DigitalInput indexFrontSensor = new DigitalInput(DIOPort.FRONT_SENSOR.id);

	private static class IndexIntakeSelector {
		IndexIntakeSelector() {
			indexFrontMotor = tryGetMotor(IndexIntakeModule.ONE.getIndexCANID());
			indexBackMotor = tryGetMotor(IndexIntakeModule.TWO.getIndexCANID());
			intakeFrontMotor = tryGetMotor(IndexIntakeModule.ONE.getIntakeCANID());
			intakeBackMotor = tryGetMotor(IndexIntakeModule.TWO.getIntakeCANID());
			if (indexFrontMotor == null) {
				indexFrontMotor = tryGetMotor(IndexIntakeModule.THREE.getIndexCANID());
				intakeFrontMotor = tryGetMotor(IndexIntakeModule.THREE.getIntakeCANID());
			} else if (indexBackMotor == null) {
				indexBackMotor = tryGetMotor(IndexIntakeModule.THREE.getIndexCANID());
				intakeBackMotor = tryGetMotor(IndexIntakeModule.THREE.getIntakeCANID());
			}
		}

		private CANSparkMax tryGetMotor(int motorId) {
			try {
				return new CANSparkMax(motorId, MotorType.kBrushless);
			} catch (Exception ignored) {
				return null;
			}
		}
	}

	public static IndexIntakeSelector indexSelector = new IndexIntakeSelector();

	// INDEXER CONTROLS THESE NOT INTAKE FYI
	public static DigitalInput intakeFrontSensor = new DigitalInput(DIOPort.INTAKE_FRONT_SENSOR.id);
	public static DigitalInput intakeBackSensor = new DigitalInput(DIOPort.INTAKE_BACK_SENSOR.id);

	// Turret Subsystem
	// ------------------------------------------------------------------------------
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(CANBus.TURRET.id);

	// Flywheel Subsystem
	// ------------------------------------------------------------------------------
	public static CANSparkMax flywheelLeftMotor = new CANSparkMax(CANBus.FLYWHEEL_LEFT.id, MotorType.kBrushless);

	public static CANSparkMax flywheelRightMotor = new CANSparkMax(CANBus.FLYWHEEL_RIGHT.id, MotorType.kBrushless);

	// Hood Subsystem
	// -----------------------------------------------------------------------------
	public static Servo hoodServo1 = new Servo(PWMPort.HOOD_SERVO_1.id);
	public static Servo hoodServo2 = new Servo(PWMPort.HOOD_SERVO_2.id);

	// Intake Subsystem
	// -------------------------------------------------------------------------------
	public static Solenoid intakeFrontSolenoid = new Solenoid(PneumaticPort.INTAKE_FRONT_UP.id);
	public static Solenoid intakeBackSolenoid = new Solenoid(PneumaticPort.INTAKE_BACK_UP.id);

	// Lift Subsystem
	// -------------------------------------------------------------------------------
	public static DoubleSolenoid liftDoubleSolenoid = new DoubleSolenoid(PneumaticPort.LIFT_UP.id, PneumaticPort.LIFT_DOWN.id);

	// CONTROL PANEL SUBSYSTEM
	// ----------------------------------------------------------------------
	// Control Panel I2C
	public static I2C.Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;

	public static ColorSensorV3 colorSensor = new ColorSensorV3(COLOR_SENSOR_PORT);
	public static ColorMatch colorMatcher = new ColorMatch();

	public static WPI_TalonFX colorSensorMotor = new WPI_TalonFX(CANBus.CONTROL_PANEL.id);

	// Limelight subsystem
	// ----------------------------------------------------------------------------------------------
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.create().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.ON, CamMode.VISION_PROCESSER,
			Pipeline.FOUR, StreamMode.STANDARD, SnapshotMode.OFF);

	// Compressor
	public static Compressor compressor = new Compressor();

	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(m_robotContainer);

}
