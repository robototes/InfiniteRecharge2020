package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ControlPanelColorSubsystem;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI
	public OI m_OI;

	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;

	public LiftSubsystem liftSubsystem;

	public DriveBaseSubsystem driveBaseSubsystem;

	public IntakeMotorOnOffSubsystem backIntakeMotorOnOffSubsystem;

	public IntakeMotorOnOffSubsystem frontIntakeMotorOnOffSubsystem;

	public IntakeUpDownSubsystem intakeUpDownSubsystem;

	public ControlPanelColorSubsystem controlPanelColorSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown);

		driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.robotDrive, RobotMap.gyro);

		backIntakeMotorOnOffSubsystem = new IntakeMotorOnOffSubsystem(RobotMap.intakeBack);

		frontIntakeMotorOnOffSubsystem = new IntakeMotorOnOffSubsystem(RobotMap.intakeFront);

		intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.intakeUpDown);

		controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor);

		// create an OI object
		m_OI = new OI();
	}
}
