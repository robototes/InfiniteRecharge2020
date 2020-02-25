package frc.team2412.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class
public class RobotMap {

	public static boolean CLIMB_CONNECTED = false;
	public static boolean CONTROL_PANEL_CONNECTED = false;
	public static boolean SHOOTER_CONNECTED = false;
	public static boolean INDEX_CONNECTED = true;
	public static boolean INTAKE_CONNECTED = true;
	public static boolean LIFT_CONNECTED = true;
	public static boolean DRIVE_BASE_CONNECTED = true;

	public static enum CANBus {
		INTAKE1(11), INDEX1(12), INTAKE2(21), INDEX2(22), INTAKE3(31), INDEX3(32), INDEX_MID(40), DRIVE_LEFT_FRONT(1),
		DRIVE_LEFT_BACK(2), DRIVE_RIGHT_FRONT(3), DRIVE_RIGHT_BACK(4), CLIMB1(5), CLIMB2(6), TURRET(7),
		FLYWHEEL_LEFT(8), FLYWHEEL_RIGHT(9), CONTROL_PANEL(10);

		public final int id;

		private CANBus(int canBusId) {
			id = canBusId;
		}
	}

	public static enum PneumaticPort {
		DRIVE(0), CLIMB_LEFT(1), CLIMB_RIGHT(2), INTAKE_FRONT_UP(6), INTAKE_BACK_UP(7), LIFT_UP(5), LIFT_DOWN(4);

		public final int id;

		private PneumaticPort(int pneumaticPortId) {
			id = pneumaticPortId;
		}
	}

	public static enum DIOPort {
		BACK_SENSOR(5), BACK_MID_SENSOR(6), BACK_INNER_SENSOR(7), FRONT_INNER_SENSOR(3), FRONT_MID_SENSOR(2),
		FRONT_SENSOR(1), INTAKE_FRONT_SENSOR(0), INTAKE_BACK_SENSOR(4);

		public final int id;

		private DIOPort(int dioPortId) {
			id = dioPortId;
		}
	}

	public static enum PWMPort {
		HOOD_SERVO_1(0), HOOD_SERVO_2(1);

		public final int id;

		private PWMPort(int pwmPortId) {
			id = pwmPortId;
		}
	}

	// CHOICE FOR INDEX/INTAKE MODULE
	public static enum IndexIntakeModule {
		ONE(CANBus.INTAKE1.id, CANBus.INDEX1.id), TWO(CANBus.INTAKE2.id, CANBus.INDEX2.id),
		THREE(CANBus.INTAKE3.id, CANBus.INDEX3.id);

		private int indexCANID, intakeCANID;

		IndexIntakeModule(int intakeID, int indexID) {
			indexCANID = indexID;
			intakeCANID = intakeID;
		}

		public int getIntakeCANID() {
			return intakeCANID;
		}

		public int getIndexCANID() {
			return indexCANID;
		}
	}

	// SET THESE TO CHANGE MODULE
	public static IndexIntakeModule frontIndexIntakeModule = IndexIntakeModule.ONE;
	public static IndexIntakeModule backIndexIntakeModule = IndexIntakeModule.TWO;

	// NOT THIS ONE
	public static IndexIntakeModule spareIndexIntakeModule = IndexIntakeModule.THREE;

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------

	// DriveBase Motors
	public static WPI_TalonFX driveLeftFront = DRIVE_BASE_CONNECTED ? new WPI_TalonFX(CANBus.DRIVE_LEFT_FRONT.id)
			: null;
	public static WPI_TalonFX driveLeftBack = DRIVE_BASE_CONNECTED ? new WPI_TalonFX(CANBus.DRIVE_LEFT_BACK.id) : null;
	public static WPI_TalonFX driveRightFront = DRIVE_BASE_CONNECTED ? new WPI_TalonFX(CANBus.DRIVE_RIGHT_FRONT.id)
			: null;
	public static WPI_TalonFX driveRightBack = DRIVE_BASE_CONNECTED ? new WPI_TalonFX(CANBus.DRIVE_RIGHT_BACK.id)
			: null;

	// DriveBase
	public static final ADIS16448_IMU driveGyro = DRIVE_BASE_CONNECTED ? new ADIS16448_IMU() : null;

	// DriveBase Solenoid
	public static Solenoid driveSolenoid = DRIVE_BASE_CONNECTED ? new Solenoid(PneumaticPort.DRIVE.id) : null;

	// climb Pneumatics
	public static Solenoid climbLeftPneumatic = CLIMB_CONNECTED ? new Solenoid(PneumaticPort.CLIMB_LEFT.id) : null;
	public static Solenoid climbRightPneumatic = CLIMB_CONNECTED ? new Solenoid(PneumaticPort.CLIMB_RIGHT.id) : null;

	// Motors
	public static CANSparkMax leftClimbMotor = CLIMB_CONNECTED ? new CANSparkMax(CANBus.CLIMB1.id, MotorType.kBrushless)
			: null;
	public static CANSparkMax rightClimbMotor = CLIMB_CONNECTED
			? new CANSparkMax(CANBus.CLIMB2.id, MotorType.kBrushless)
			: null;

	// INDEX SUBSYSTEM
	// ---------------------------------------------------------------------------

	// motors
	public static CANSparkMax indexFrontMotor, indexBackMotor;
	public static CANSparkMax indexMidMotor = INDEX_CONNECTED
			? new CANSparkMax(CANBus.INDEX_MID.id, MotorType.kBrushless)
			: null;

	public static CANSparkMax intakeFrontMotor, intakeBackMotor;

	// sensors
	public static DigitalInput back = INDEX_CONNECTED ? new DigitalInput(DIOPort.BACK_SENSOR.id) : null;
	public static DigitalInput backMid = INDEX_CONNECTED ? new DigitalInput(DIOPort.BACK_MID_SENSOR.id) : null;
	public static DigitalInput backInner = INDEX_CONNECTED ? new DigitalInput(DIOPort.BACK_INNER_SENSOR.id) : null;
	public static DigitalInput frontInner = INDEX_CONNECTED ? new DigitalInput(DIOPort.FRONT_INNER_SENSOR.id) : null;
	public static DigitalInput frontMid = INDEX_CONNECTED ? new DigitalInput(DIOPort.FRONT_MID_SENSOR.id) : null;
	public static DigitalInput front = INDEX_CONNECTED ? new DigitalInput(DIOPort.FRONT_SENSOR.id) : null;

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
	public static DigitalInput intakeFront = INTAKE_CONNECTED ? new DigitalInput(DIOPort.INTAKE_FRONT_SENSOR.id) : null;
	public static DigitalInput intakeBack = INTAKE_CONNECTED ? new DigitalInput(DIOPort.INTAKE_BACK_SENSOR.id) : null;

	// Turret Subsystem
	// ------------------------------------------------------------------------------
	public static WPI_TalonSRX turretMotor = SHOOTER_CONNECTED ? new WPI_TalonSRX(CANBus.TURRET.id) : null;

	// Flywheel Subsystem
	// ------------------------------------------------------------------------------
	public static CANSparkMax flywheelLeftMotor = SHOOTER_CONNECTED
			? new CANSparkMax(CANBus.FLYWHEEL_LEFT.id, MotorType.kBrushless)
			: null;

	public static CANSparkMax flywheelRightMotor = SHOOTER_CONNECTED
			? new CANSparkMax(CANBus.FLYWHEEL_RIGHT.id, MotorType.kBrushless)
			: null;

	// Hood Subsystem
	// -----------------------------------------------------------------------------
	public static Servo hoodServo1 = SHOOTER_CONNECTED ? new Servo(PWMPort.HOOD_SERVO_1.id) : null;
	public static Servo hoodServo2 = SHOOTER_CONNECTED ? new Servo(PWMPort.HOOD_SERVO_2.id) : null;

	// Intake Subsystem
	// -------------------------------------------------------------------------------
	public static Solenoid frontIntakeliftSolenoid = INTAKE_CONNECTED ? new Solenoid(PneumaticPort.INTAKE_FRONT_UP.id)
			: null;
	public static Solenoid backIntakeLiftSolenoid = INTAKE_CONNECTED ? new Solenoid(PneumaticPort.INTAKE_BACK_UP.id)
			: null;

	// Lift Subsystem
	// -------------------------------------------------------------------------------
	public static DoubleSolenoid liftUpDown = LIFT_CONNECTED
			? new DoubleSolenoid(PneumaticPort.LIFT_UP.id, PneumaticPort.LIFT_DOWN.id)
			: null;

	// CONTROL PANEL SUBSYSTEM
	// ----------------------------------------------------------------------
	// Control Panel I2C
	public static I2C.Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;

	public static ColorSensorV3 colorSensor = CONTROL_PANEL_CONNECTED ? new ColorSensorV3(COLOR_SENSOR_PORT) : null;
	public static ColorMatch colorMatcher = CONTROL_PANEL_CONNECTED ? new ColorMatch() : null;

	public static WPI_TalonFX colorSensorMotor = CONTROL_PANEL_CONNECTED ? new WPI_TalonFX(CANBus.CONTROL_PANEL.id)
			: null;

	// Limelight subsystem
	// ----------------------------------------------------------------------------------------------
	public static NetworkTable limelightNetworkTable = SHOOTER_CONNECTED
			? NetworkTableInstance.create().getTable("limelight")
			: null;
	public static Limelight limelight = SHOOTER_CONNECTED
			? new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER, Pipeline.ZERO,
					StreamMode.STANDARD, SnapshotMode.OFF)
			: null;

	// Unknown RN
	// -------------------------------------------------------------------------------------------------
	// Compressor
	public static Compressor compressor = new Compressor();

	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	public static RobotState m_robotState = new RobotState(m_robotContainer);

	// OI
	public static OI m_OI = new OI(m_robotContainer);

}
