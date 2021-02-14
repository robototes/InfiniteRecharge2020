package frc.team2412.robot;

import static frc.team2412.robot.RobotMap.climbLeftMotor;
import static frc.team2412.robot.RobotMap.climbLeftSolenoid;
import static frc.team2412.robot.RobotMap.climbRightMotor;
import static frc.team2412.robot.RobotMap.climbRightSolenoid;
import static frc.team2412.robot.RobotMap.colorSensor;
import static frc.team2412.robot.RobotMap.colorSensorMotor;
import static frc.team2412.robot.RobotMap.driveGyro;
import static frc.team2412.robot.RobotMap.driveLeftBackMotor;
import static frc.team2412.robot.RobotMap.driveLeftFrontMotor;
import static frc.team2412.robot.RobotMap.driveRightBackMotor;
import static frc.team2412.robot.RobotMap.driveRightFrontMotor;
import static frc.team2412.robot.RobotMap.driveSolenoid;
import static frc.team2412.robot.RobotMap.flywheelLeftMotor;
import static frc.team2412.robot.RobotMap.flywheelRightMotor;
import static frc.team2412.robot.RobotMap.hoodServo1;
import static frc.team2412.robot.RobotMap.hoodServo2;
import static frc.team2412.robot.RobotMap.indexBackInnerSensor;
import static frc.team2412.robot.RobotMap.indexBackMidSensor;
import static frc.team2412.robot.RobotMap.indexBackMotor;
import static frc.team2412.robot.RobotMap.indexBackSensor;
import static frc.team2412.robot.RobotMap.indexFrontInnerSensor;
import static frc.team2412.robot.RobotMap.indexFrontMidSensor;
import static frc.team2412.robot.RobotMap.indexFrontMotor;
import static frc.team2412.robot.RobotMap.indexFrontSensor;
import static frc.team2412.robot.RobotMap.indexLeftMidMotor;
import static frc.team2412.robot.RobotMap.indexRightMidMotor;
import static frc.team2412.robot.RobotMap.intakeBackMotor;
import static frc.team2412.robot.RobotMap.intakeBackSensor;
import static frc.team2412.robot.RobotMap.intakeBackSolenoid;
import static frc.team2412.robot.RobotMap.intakeFrontMotor;
import static frc.team2412.robot.RobotMap.intakeFrontSensor;
import static frc.team2412.robot.RobotMap.intakeFrontSolenoid;
import static frc.team2412.robot.RobotMap.liftDoubleSolenoid;
import static frc.team2412.robot.RobotMap.limelight;
import static frc.team2412.robot.RobotMap.turretMotor;
import static frc.team2412.robot.RobotMapConstants.CLIMB_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.CONTROL_PANEL_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.DRIVE_BASE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INDEX_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INTAKE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.LIFT_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.SHOOTER_CONNECTED;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2412.robot.subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.subsystems.ControlPanelColorSubsystem;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {

	public LimelightSubsystem m_limelightSubsystem;

	public TurretSubsystem m_turretSubsystem;

	public FlywheelSubsystem m_flywheelSubsystem;

	public HoodSubsystem m_hoodSubsystem;

	public LiftSubsystem m_liftSubsystem;

	public DriveBaseSubsystem m_driveBaseSubsystem;

	public IntakeMotorSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeLiftSubsystem m_intakeUpDownSubsystem;

	public ControlPanelColorSubsystem m_controlPanelColorSubsystem;

	public IndexerSubsystemSuperStructure m_indexerMotorSubsystem;

	public IndexerSensorSubsystem m_indexerSensorSubsystem;

	public ClimbLiftSubsystem m_climbLiftSubsystem;

	public ClimbMotorSubsystem m_climbMotorSubsystem;

	// A chooser for autonomous commands
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public RobotContainer() {
		if (CLIMB_CONNECTED) {
			configureClimb();
		}

		if (INDEX_CONNECTED) {
			configureIndexer();
		}

		if (LIFT_CONNECTED) {
			configureLift();
		}

		if (DRIVE_BASE_CONNECTED) {
			configureDrivebase();
		}

		if (INTAKE_CONNECTED) {
			configureIntake();
		}

		if (CONTROL_PANEL_CONNECTED) {
			configureControlPanel();
		}

		if (SHOOTER_CONNECTED) {
			configureShooter();
		}
	}

	private void configureLift() {
		m_liftSubsystem = new LiftSubsystem(liftDoubleSolenoid);
	}

	private void configureShooter() {
		m_limelightSubsystem = new LimelightSubsystem(limelight);
		m_turretSubsystem = new TurretSubsystem(turretMotor, m_limelightSubsystem);

		m_flywheelSubsystem = new FlywheelSubsystem(flywheelLeftMotor, flywheelRightMotor);

		m_hoodSubsystem = new HoodSubsystem(hoodServo1, hoodServo2);
	}

	private void configureControlPanel() {
		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(colorSensor, colorSensorMotor);
	}

	private void configureIntake() {
		m_intakeMotorOnOffSubsystem = new IntakeMotorSubsystem(intakeFrontMotor, intakeBackMotor);

		m_intakeUpDownSubsystem = new IntakeLiftSubsystem(intakeFrontSolenoid, intakeBackSolenoid);
	}

	private void configureDrivebase() {
		m_driveBaseSubsystem = new DriveBaseSubsystem(driveSolenoid, driveGyro, driveLeftFrontMotor, driveLeftBackMotor,
				driveRightFrontMotor, driveRightBackMotor);
	}

	private void configureIndexer() {
		m_indexerSensorSubsystem = new IndexerSensorSubsystem(intakeFrontSensor, indexFrontSensor, indexFrontMidSensor,
				indexFrontInnerSensor, indexBackInnerSensor, indexBackMidSensor, indexBackSensor, intakeBackSensor);

		m_indexerMotorSubsystem = new IndexerSubsystemSuperStructure(indexFrontMotor, indexLeftMidMotor,
				indexRightMidMotor, indexBackMotor, m_indexerSensorSubsystem);
	}

	private void configureClimb() {
		m_climbLiftSubsystem = new ClimbLiftSubsystem(climbLeftSolenoid, climbRightSolenoid);
		m_climbMotorSubsystem = new ClimbMotorSubsystem(climbLeftMotor, climbRightMotor);
	}

}
