package frc.team2412.robot;

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
		m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown);
	}

	private void configureShooter() {
		m_limelightSubsystem = new LimelightSubsystem(RobotMap.limelight);
		m_turretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_limelightSubsystem);

		m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);

		m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo1, RobotMap.hoodServo2);
	}

	private void configureControlPanel() {
		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor);
	}

	private void configureIntake() {
		m_intakeMotorOnOffSubsystem = new IntakeMotorSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);

		m_intakeUpDownSubsystem = new IntakeLiftSubsystem(RobotMap.frontIntakeliftSolenoid,
				RobotMap.backIntakeLiftSolenoid);
	}

	private void configureDrivebase() {
		m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.driveSolenoid, RobotMap.driveGyro,
				RobotMap.driveLeftFront, RobotMap.driveLeftBack, RobotMap.driveRightFront, RobotMap.driveRightBack);
	}

	private void configureIndexer() {
		m_indexerSensorSubsystem = new IndexerSensorSubsystem(RobotMap.intakeFront, RobotMap.front, RobotMap.frontMid,
				RobotMap.frontInner, RobotMap.backInner, RobotMap.backMid, RobotMap.back, RobotMap.intakeBack);

		m_indexerMotorSubsystem = new IndexerSubsystemSuperStructure(RobotMap.indexFrontMotor,
				RobotMap.indexLeftMidMotor, RobotMap.indexRightMidMotor, RobotMap.indexBackMotor,
				m_indexerSensorSubsystem);
	}

	private void configureClimb() {
		m_climbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.climbLeftPneumatic, RobotMap.climbRightPneumatic);
		m_climbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);
	}

}
