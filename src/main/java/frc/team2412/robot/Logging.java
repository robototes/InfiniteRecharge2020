package frc.team2412.robot;

import edu.wpi.first.wpilibj.RobotController;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Logging implements Loggable {

	Robot m_robot;

	// 4 rows, 10 col

	private static final String green = "#7FFF00";
	private static final String red = "#B22222";
	private static final String white = "#FFFFFF";

	// Goal Able
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public static boolean outerGoalAble = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public static boolean innerGoalAble = false;

	// Goal Aimed
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public static boolean outerGoalAimed = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Dwivew view >~<")
	public static boolean innerGoalAimed = false;

	// Intake stuff
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 3, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public static boolean backIntakeOn = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 4, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public static boolean backIntakeUp = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 6, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public static boolean frontIntakeUp = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 7, rowIndex = 0, height = 1, tabName = "Dwivew view >~<")
	public static boolean frontIntakeOn = false;

	// PC count
	@Log(columnIndex = 5, height = 1, rowIndex = 0, tabName = "Dwivew view >~<")
	public static int powerCellCount = 0;

	// Dial
	@Log.Dial(columnIndex = 8, height = 1, rowIndex = 3, tabName = "Dwivew view >~<")
	public static double currentDrawDial = 0;

	// Dial Boolean
	@Log.BooleanBox(colorWhenFalse = green, colorWhenTrue = red, columnIndex = 7, rowIndex = 1, tabName = "Dwivew view >~<")
	@Log.BooleanBox(colorWhenFalse = green, colorWhenTrue = red, columnIndex = 9, rowIndex = 1, tabName = "Dwivew view >~<")
	public static boolean brownoutWarning = false;

	// Timer
	@Log(columnIndex = 7, rowIndex = 2, width = 3, height = 1, tabName = "Dwivew view >~<")
	public static String timer = "Yes";

	// Gauge
	@Log(columnIndex = 7, rowIndex = 3, tabName = "Dwivew view >~<")
	public static double PSI = 0;

	// CurrentDraws
	@Log(columnIndex = 8, rowIndex = 3, tabName = "Dwivew view >~<")
	public static double driveBaseCurrentDraw = 0;

	@Log(columnIndex = 9, rowIndex = 3, tabName = "Dwivew view >~<")
	public static double totalCurrentDraw = 0;

	public Logging(Robot robot) {
		this.m_robot = robot;
	}

	public void periodic() {
		// WIP: OuterGoalAble, innerGoalAble, outerGoalAimed, innerGoalAimed

		backIntakeOn = RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem.backMotorOn();
		backIntakeUp = RobotMap.m_robotContainer.m_intakeUpDownSubsystem.isBackIntakeUp();
		frontIntakeUp = RobotMap.m_robotContainer.m_intakeUpDownSubsystem.isFrontIntakeUp();
		frontIntakeOn = RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem.FrontMotorOn();

		powerCellCount = RobotState.m_ballCount;

		currentDrawDial = (RobotMap.CLIMB_CONNECTED ? RobotMap.m_robotContainer.m_climbMotorSubsystem.getCurrentDraw()
				: 0)
				+ (RobotMap.DRIVE_BASE_CONNECTED ? RobotMap.m_robotContainer.m_driveBaseSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.SHOOTER_CONNECTED ? RobotMap.m_robotContainer.m_flywheelSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.INDEX_CONNECTED ? RobotMap.m_robotContainer.m_indexerMotorSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.INTAKE_CONNECTED ? RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem.getCurrentDraw()
						: 0)
				+ (RobotMap.SHOOTER_CONNECTED ? RobotMap.m_robotContainer.m_turretSubsystem.getCurrentDraw() : 0)
				+ RobotMap.compressor.getCompressorCurrent();

		// 6.8 V is the warning level for brownout
		brownoutWarning = (RobotController.getInputVoltage() < 7 || RobotController.isBrownedOut());

		double time = m_robot.timeRemaining;
		timer = time / 60 + " : " + time % 60;

		driveBaseCurrentDraw = RobotMap.m_robotContainer.m_driveBaseSubsystem.getCurrentDraw();
	}

}
