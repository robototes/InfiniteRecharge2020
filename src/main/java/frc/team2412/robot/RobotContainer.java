package frc.team2412.robot;

import frc.team2412.robot.Commands.ClimbCommands.ClimbCommand;
import frc.team2412.robot.Commands.ClimbCommands.ClimbRails;
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

	public ClimbLiftSubsystem m_ClimbLiftSubsystem;
	public ClimbMotorSubsystem m_ClimbMotorSubsystem;
	public ClimbCommand m_ClimbCommand;
	public ClimbRails m_ClimbRails;
	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;

	@Log(name = "Limelight Subsystem")
	public LimelightSubsystem m_LimelightSubsystem;

	@Log(name = "Turret Subsystem")
	public TurretSubsystem m_TurretSubsystem;

	@Log(name = "Flywheel Subsystem")
	public FlywheelSubsystem m_FlywheelSubsystem;

	@Log(name = "Hood Subsystem")
	public HoodSubsystem m_HoodSubsystem;

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

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.leftPneumatic, RobotMap.rightPneumatic);
		m_ClimbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);

		m_IndexerSubsystem = new IndexerSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
				RobotMap.indexBackMotor, RobotMap.front, RobotMap.frontMid, RobotMap.mid, RobotMap.backMid,
				RobotMap.back, RobotMap.intakeFront, RobotMap.intakeBack);

		m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown);

		m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.robotDrive, RobotMap.gyro, RobotMap.m_OI.driverStick);

		m_intakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);

		m_intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.intakeUpDown);

		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor,
				RobotMap.colorMatcher);

		m_TurretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);

		m_FlywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelMotor1, RobotMap.flywheelMotor2);

		m_HoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);

	}
}
