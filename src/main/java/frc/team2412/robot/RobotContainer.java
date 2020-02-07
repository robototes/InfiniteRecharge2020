package frc.team2412.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2412.robot.Subsystems.AutonumousSubsystem;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.Subsystems.ControlPanelColorSubsystem;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// Subsystems
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

	@Log(name = "Indexer Subsystem")
	public IndexerSubsystem m_IndexerSubsystem;

	@Log(name = "Climb lift Subsystem")
	public ClimbLiftSubsystem m_ClimbLiftSubsystem;

	@Log(name = "Climb Motor Subsystem")
	public ClimbMotorSubsystem m_ClimbMotorSubsystem;

	@Log(name = "Autonumous Subsystem", tabName = "robotContainer")
	@Log(tabName = "Robot")
	public AutonumousSubsystem m_autonumousSubsystem;

	// A chooser for autonomous commands
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.climbLeftPneumatic, RobotMap.climbRightPneumatic);
		m_ClimbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);

		m_IndexerSubsystem = new IndexerSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
				RobotMap.indexBackMotor, RobotMap.front, RobotMap.frontMid, RobotMap.mid, RobotMap.backMid,
				RobotMap.back, RobotMap.intakeFront, RobotMap.intakeBack);

		m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown, RobotMap.compressor);

		m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.gyro, RobotMap.leftFront, RobotMap.leftBack,
				RobotMap.rightFront, RobotMap.rightBack);

		m_intakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);

		m_intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.frontIntakeUpDown, RobotMap.backIntakeUpDown,
				RobotMap.compressor);

		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor);

		m_turretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);

		m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);

		m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);

		m_autonumousSubsystem = new AutonumousSubsystem(m_driveBaseSubsystem, m_liftSubsystem, m_turretSubsystem,
				m_flywheelSubsystem, m_hoodSubsystem, m_IndexerSubsystem, m_intakeMotorOnOffSubsystem,
				m_intakeUpDownSubsystem);

		// Add commands to the autonomous command chooser
//		m_chooser.addOption("Basic Auto", m_basicAutoCommand);

	}
}
