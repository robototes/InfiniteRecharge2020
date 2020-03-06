package frc.team2412.robot;

import static frc.team2412.robot.RobotMap.CLIMB_CONNECTED;
import static frc.team2412.robot.RobotMap.DRIVE_BASE_CONNECTED;
import static frc.team2412.robot.RobotMap.INDEX_CONNECTED;
import static frc.team2412.robot.RobotMap.INTAKE_CONNECTED;
import static frc.team2412.robot.RobotMap.SHOOTER_CONNECTED;
import static frc.team2412.robot.RobotMap.compressor;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Logging implements Loggable, Sendable {

	
	RobotContainer m_robotContainer;
	
	// 4 rows, 10 col
	
	

	@Log.PDP(tabName = "Misc.")
	public PowerDistributionPanel pdp = new PowerDistributionPanel();

	private final String green = "#7FFF00";
	private final String red = "#B22222";
	private final String white = "#FFFFFF";

	// Goal Able
	@Log.BooleanBox(name = "Outer goal possible", colorWhenFalse = white, colorWhenTrue = green, width = 3, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public boolean outerGoalAble = false;
	@Log.BooleanBox(name = "Inner goal possible", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public boolean innerGoalAble = false;

	// Goal Aimed
	@Log.BooleanBox(name = "Outer goal ready", colorWhenFalse = white, colorWhenTrue = green, width = 3, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public boolean outerGoalAimed = false;
	@Log.BooleanBox(name = "Inner goal ready", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public boolean innerGoalAimed = false;

	// Intake stuff
	@Log.BooleanBox(name = "Back intake On", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 3, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public boolean backIntakeOn = false;
	@Log.BooleanBox(name = "Back intake Up", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 4, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public boolean backIntakeUp = false;
	@Log.BooleanBox(name = "Front intake Up", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 6, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public boolean frontIntakeUp = false;
	@Log.BooleanBox(name = "Front intake On", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 7, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public boolean frontIntakeOn = false;

	// PC count
	@Log(columnIndex = 5, height = 1, rowIndex = 0, tabName = "Dwivew view >~<", name = "Power cell count")
	public int powerCellCount = 0;

	// Dial Boolean
	@Log.BooleanBox(colorWhenFalse = green, name = "Brownout Warinng", colorWhenTrue = red, width = 3, columnIndex = 7, rowIndex = 1, tabName = "Dwivew view >~<")
	public boolean brownoutWarning = false;

	// Dial
	@Log.Dial(columnIndex = 8, height = 1, rowIndex = 3, tabName = "Dwivew view >~<", name = "Current draw")
	public double currentDrawDial = 0;

	// Timer
	@Log(columnIndex = 7, rowIndex = 2, width = 3, height = 1, name = "Timer", tabName = "Dwivew view >~<")
	public static String timer = "";

	// Gauge
	@Log(columnIndex = 7, rowIndex = 3, tabName = "Dwivew view >~<")
	public double PSI = 0;

	// CurrentDraws
	@Log(columnIndex = 8, rowIndex = 3, tabName = "Dwivew view >~<")
	public double driveBaseCurrentDraw = 0;

	@Log(columnIndex = 9, rowIndex = 3, tabName = "Dwivew view >~<")
	public static double totalCurrentDraw = 0;

	@Log.CameraStream(columnIndex = 3, rowIndex = 1, width = 4, height = 3, tabName = "Dwivew view >~<")
	public HttpCamera limelightFeed;

	public Logging(RobotContainer robotContainer) {
		m_robotContainer = robotContainer;
		limelightFeed = new HttpCamera("limelight", "http://10.24.12.11:5801/stream.mjpg",
				HttpCameraKind.kMJPGStreamer);
	}

	public void periodic() {
		// WIP: OuterGoalAble, innerGoalAble, outerGoalAimed, innerGoalAimed

		backIntakeOn =  INTAKE_CONNECTED ? m_robotContainer.m_intakeMotorSubsystem.backMotorOn()
				: false;
		backIntakeUp =  INTAKE_CONNECTED ? m_robotContainer.m_intakeLiftSubsystem.isBackIntakeUp()
				: false;
		frontIntakeUp =  INTAKE_CONNECTED ? m_robotContainer.m_intakeLiftSubsystem.isFrontIntakeUp()
				: false;
		frontIntakeOn =  INTAKE_CONNECTED ? m_robotContainer.m_intakeMotorSubsystem.FrontMotorOn()
				: false;

		powerCellCount = RobotState.m_ballCount % 5;

		currentDrawDial = ( CLIMB_CONNECTED ? m_robotContainer.m_climbMotorSubsystem.getCurrentDraw()
				: 0)
				+ ( DRIVE_BASE_CONNECTED ?  m_robotContainer.m_driveBaseSubsystem.getCurrentDraw() : 0)
				+ ( SHOOTER_CONNECTED ?  m_robotContainer.m_flywheelSubsystem.getCurrentDraw() : 0)
				+ ( INDEX_CONNECTED ?  m_robotContainer.m_indexerMotorSubsystem.getCurrentDraw() : 0)
				+ ( INTAKE_CONNECTED ?  m_robotContainer.m_intakeMotorSubsystem.getCurrentDraw() : 0)
				+ ( SHOOTER_CONNECTED ?  m_robotContainer.m_turretSubsystem.getCurrentDraw() : 0)
				+  compressor.getCompressorCurrent();

		// 6.8 V is the warning level for brownout
		brownoutWarning = (RobotController.getInputVoltage() < 7 || RobotController.isBrownedOut());

		// double time = m_robot.timeRemaining;
		// timer = time / 60 + " : " + time % 60;

		// driveBaseCurrentDraw =
		//  m_robotContainer.m_driveBaseSubsystem.getCurrentDraw();
	}

	@Override
	public void initSendable(SendableBuilder builder) {

	}

}
