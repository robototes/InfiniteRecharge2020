package frc.team2412.robot;

import static java.util.Map.entry;

import java.util.Map;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.Subsystems.ControlPanelColorSubsystem;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer implements Loggable {
	// Subsystems
	Command m_exampleSelectCommand = new SelectCommand(
			// Maps selector values to commands
			Map.ofEntries(entry(1, null), entry(2, null), entry(3, null)), this::select

	);

	private int select() {
		return 1;
	}

	public ExampleSubsystem m_ExampleSubsystem;

	@Log(name = "Limelight Subsystem")
	public LimelightSubsystem m_LimelightSubsystem;

	@Log(name = "Turret Subsystem")
	public TurretSubsystem m_turretSubsystem;

	@Log(name = "Flywheel Subsystem")
	public FlywheelSubsystem m_flywheelSubsystem;

	@Log(name = "Hood Subsystem")
	public HoodSubsystem m_hoodSubsystem;

	@Log(name = "Lift Subsystem")
	public LiftSubsystem m_liftSubsystem;

	@Log(name = "Drivebase Subsystem")
	public DriveBaseSubsystem m_driveBaseSubsystem;

	@Log(name = "Intake motor Subsystem")
	public IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	@Log(name = "Intake lift Subsystem")
	public IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	@Log(name = "Control Panel Subsystem")
	public ControlPanelColorSubsystem m_controlPanelColorSubsystem;

	@Log(name = "Indexer Motor Subsystem")
	public IndexerMotorSubsystem m_IndexerMotorSubsystem;

	@Log(name = "Indexer Sensor Subsystem")
	public IndexerSensorSubsystem m_IndexerSensorSubsystem;

	@Log(name = "Climb lift Subsystem")
	public ClimbLiftSubsystem m_ClimbLiftSubsystem;

	@Log(name = "Climb Motor Subsystem")
	public ClimbMotorSubsystem m_ClimbMotorSubsystem;

	// A chooser for autonomous commands
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.climbLeftPneumatic, RobotMap.climbRightPneumatic);
		m_ClimbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);

		m_IndexerMotorSubsystem = new IndexerMotorSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
				RobotMap.indexBackMotor);

		m_IndexerSensorSubsystem = new IndexerSensorSubsystem(RobotMap.intakeFront, RobotMap.front, RobotMap.frontMid,
				RobotMap.mid, RobotMap.backMid, RobotMap.back, RobotMap.intakeBack);

		m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown, RobotMap.compressor);

		m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.driveSolenoid, RobotMap.driveGyro,
				RobotMap.driveLeftFront, RobotMap.driveLeftBack, RobotMap.driveRightFront, RobotMap.driveRightBack);

		m_intakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);

		m_intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.frontIntakeliftSolenoid,
				RobotMap.backIntakeLiftSolenoid, RobotMap.compressor);

		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor);

		m_turretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);

		m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);

		m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);

		// Add commands to the autonomous command chooser
//		m_chooser.addOption("Basic Auto", m_basicAutoCommand);

	}
}
