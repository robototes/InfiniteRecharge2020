package frc.team2412.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer implements Loggable {
	
//	public LimelightSubsystem m_LimelightSubsystem;
//
//	public TurretSubsystem m_turretSubsystem;
//
//	public FlywheelSubsystem m_flywheelSubsystem;
//
//	public HoodSubsystem m_hoodSubsystem;
//
//	public LiftSubsystem m_liftSubsystem;

//	@Log(tabName = "Drivebase Subsystem", name = "Drivebase Subsystem")
	public DriveBaseSubsystem m_driveBaseSubsystem;

//	public IntakeOnOffSubsystem m_intakeMotorOnOffSubsystem;
//
//	public IntakeUpDownSubsystem m_intakeUpDownSubsystem;
//
//	public ControlPanelColorSubsystem m_controlPanelColorSubsystem;
//
//	public IndexerMotorSubsystem m_IndexerMotorSubsystem;
//
//	public IndexerSensorSubsystem m_IndexerSensorSubsystem;
//
//	public ClimbLiftSubsystem m_ClimbLiftSubsystem;
//
//	public ClimbMotorSubsystem m_ClimbMotorSubsystem;

	// A chooser for autonomous commands
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public RobotContainer() {
//		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.climbLeftPneumatic, RobotMap.climbRightPneumatic);
//		m_ClimbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);
//
//		m_IndexerMotorSubsystem = new IndexerMotorSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
//				RobotMap.indexBackMotor);
//
//		m_IndexerSensorSubsystem = new IndexerSensorSubsystem(RobotMap.intakeFront, RobotMap.front, RobotMap.frontMid,
//				RobotMap.mid, RobotMap.backMid, RobotMap.back, RobotMap.intakeBack);
//
//		m_liftSubsystem = new LiftSubsystem(RobotMap.liftUpDown, RobotMap.compressor);

		m_driveBaseSubsystem = new DriveBaseSubsystem(RobotMap.driveSolenoid, RobotMap.driveGyro,
				RobotMap.driveLeftFront, RobotMap.driveLeftBack, RobotMap.driveRightFront, RobotMap.driveRightBack);

//		m_intakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(RobotMap.intakeFrontMotor, RobotMap.intakeBackMotor);
//
//		m_intakeUpDownSubsystem = new IntakeUpDownSubsystem(RobotMap.frontIntakeliftSolenoid,
//				RobotMap.backIntakeLiftSolenoid, RobotMap.compressor);
//
//		m_controlPanelColorSubsystem = new ControlPanelColorSubsystem(RobotMap.colorSensor, RobotMap.colorSensorMotor);
//
//		m_turretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);
//
//		m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);
//
//		m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);

		// Add commands to the autonomous command chooser
//		m_chooser.addOption("Basic Auto", m_basicAutoCommand);

	}
}
