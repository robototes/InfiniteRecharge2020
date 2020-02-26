package frc.team2412.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2412.robot.subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.subsystems.ControlPanelColorSubsystem;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;
import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer implements Loggable {
	@Log(tabName = "Turret")
	public LimelightSubsystem m_LimelightSubsystem;

	@Log(tabName = "Turret Subsystem", name = "Turret")
	public TurretSubsystem m_turretSubsystem;

	@Log(tabName = "Flywheel Subsystem", name = "Turret")
	public FlywheelSubsystem m_flywheelSubsystem;

	@Log(tabName = "Hood Subsystem", name = "Turret")
	public HoodSubsystem m_hoodSubsystem;

	@Log(tabName = "Lift Subsystem", name = "Lift")
	public LiftSubsystem m_liftSubsystem;

	@Log(tabName = "Drivebase Subsystem", name = "Drivebase Subsystem")
	public DriveBaseSubsystem m_driveBaseSubsystem;

	@Log(tabName = "Intake Motors Subsystem", name = "Intake")
	public IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	@Log(tabName = "Intake Lift Subsystem", name = "Intake")
	public IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	@Log(tabName = "Control Panel Subsystem", name = "Control Panel")
	public ControlPanelColorSubsystem m_controlPanelColorSubsystem;

	@Log(tabName = "Indexer Motor Subsystem", name = "Indexer")
	public IndexerMotorSubsystem m_indexerMotorSubsystem;

	@Log(tabName = "Indexer Sensor Subsystem", name = "Indexer")
	public IndexerSensorSubsystem m_indexerSensorSubsystem;

	@Log(tabName = "Climb Lift Subsystem", name = "Climb")
	public ClimbLiftSubsystem m_climbLiftSubsystem;

	@Log(tabName = "Climb Motor Subsystem", name = "Climb")
	public ClimbMotorSubsystem m_climbMotorSubsystem;

	// A chooser for autonomous commands
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public RobotContainer() {
		if (RobotMap.CLIMB_CONNECTED) {
			m_climbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.climbLeftPneumatic, RobotMap.climbRightPneumatic);
			m_climbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);
		}

		if (RobotMap.INDEX_CONNECTED) {
			m_indexerSensorSubsystem = new IndexerSensorSubsystem(RobotMap.intakeFront, RobotMap.front,
					RobotMap.frontMid, RobotMap.frontInner, RobotMap.backInner, RobotMap.backMid, RobotMap.back,
					RobotMap.intakeBack);

			m_indexerMotorSubsystem = new IndexerMotorSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
					RobotMap.indexBackMotor, m_indexerSensorSubsystem);
		}

		if (RobotMap.LIFT_CONNECTED) {
			m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown);
		}

		if (RobotMap.DRIVE_BASE_CONNECTED) {
			m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.driveSolenoid, RobotMap.driveGyro,
					RobotMap.driveLeftFront, RobotMap.driveLeftBack, RobotMap.driveRightFront, RobotMap.driveRightBack);
		}

		if (RobotMap.INTAKE_CONNECTED) {
			m_intakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);

			m_intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.frontIntakeliftSolenoid,
					RobotMap.backIntakeLiftSolenoid);
		}

		if (RobotMap.CONTROL_PANEL_CONNECTED) {
			m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor,
					RobotMap.colorSensorMotor);
		}

		if (RobotMap.SHOOTER_CONNECTED) {
			m_LimelightSubsystem = new LimelightSubsystem(RobotMap.limelight);
			m_turretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);

			m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);

			m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo1, RobotMap.hoodServo2);
		}

	}
}
