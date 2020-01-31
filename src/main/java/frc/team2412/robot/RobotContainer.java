package frc.team2412.robot;

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

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {

	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;
	public LimelightSubsystem m_LimelightSubsystem;
	public TurretSubsystem m_TurretSubsystem;
	public FlywheelSubsystem m_FlywheelSubsystem;
	public HoodSubsystem m_HoodSubsystem;

	public LiftSubsystem m_liftSubsystem;

	public DriveBaseSubsystem m_driveBaseSubsystem;

	public IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;

	public IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public ControlPanelColorSubsystem m_controlPanelColorSubsystem;

	public IndexerSubsystem m_IndexerSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

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
