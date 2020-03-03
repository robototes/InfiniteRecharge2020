package frc.team2412.robot;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Logging implements Loggable, Sendable {

	// 4 rows, 10 col

	private final String green = "#7FFF00";
	private final String red = "#B22222";
	private final String white = "#FFFFFF";

	// Goal Able
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Driver View")
	@Log.BooleanBox(name = "asdf", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2, tabName = "Driver View")
	public boolean outerGoalAble = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Driver View")
	public boolean innerGoalAble = false;

	// Goal Aimed
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Driver View")
	@Log.BooleanBox(name = "asdf2", colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2, tabName = "Driver View")
	public boolean outerGoalAimed = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2, tabName = "Driver View")
	public boolean innerGoalAimed = false;

	// Intake stuff
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 3, rowIndex = 0, height = 1, tabName = "Driver View")
	public boolean backIntakeOn = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 4, rowIndex = 0, height = 1, tabName = "Driver View")
	public boolean backIntakeUp = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 6, rowIndex = 0, height = 1, tabName = "Driver View")
	public boolean frontIntakeUp = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 7, rowIndex = 0, height = 1, tabName = "Driver View")
	public boolean frontIntakeOn = false;

	// PC count
	@Log(columnIndex = 5, height = 1, rowIndex = 0, tabName = "Driver View")
	public int powerCellCount = 0;

	// Dial
	@Log.Dial(columnIndex = 8, height = 1, rowIndex = 3, tabName = "Driver View")
	public double currentDrawDial = 0;

	// Dial Boolean
	@Log.BooleanBox(colorWhenFalse = green, colorWhenTrue = red, columnIndex = 7, rowIndex = 1, tabName = "Driver View")
	//@Log.BooleanBox(colorWhenFalse = green, colorWhenTrue = red, columnIndex = 9, rowIndex = 1, tabName = "Driver View")
	public boolean brownoutWarning = false;

	// Timer
	@Log(columnIndex = 7, rowIndex = 2, width = 3, height = 1, tabName = "Driver View")
	public String timer = "Yes";

	// Gauge
	@Log(columnIndex = 7, rowIndex = 3, tabName = "Driver View")
	public double PSI = 0;

	// CurrentDraws
	@Log(columnIndex = 8, rowIndex = 3, tabName = "Driver View")
	public double driveBaseCurrentDraw = 0;

	@Log(columnIndex = 9, rowIndex = 3, tabName = "Driver View")
	public double totalCurrentDraw = 0;

	public Logging() {
	}

	public void periodic() {
		// WIP: OuterGoalAble, innerGoalAble, outerGoalAimed, innerGoalAimed

		backIntakeOn = RobotMap.INTAKE_CONNECTED ? RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem.backMotorOn()
				: false;
		backIntakeUp = RobotMap.INTAKE_CONNECTED ? RobotMap.m_robotContainer.m_intakeUpDownSubsystem.isBackIntakeUp()
				: false;
		frontIntakeUp = RobotMap.INTAKE_CONNECTED ? RobotMap.m_robotContainer.m_intakeUpDownSubsystem.isFrontIntakeUp()
				: false;
		frontIntakeOn = RobotMap.INTAKE_CONNECTED ? RobotMap.m_robotContainer.m_intakeMotorOnOffSubsystem.FrontMotorOn()
				: false;

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

		// double time = m_robot.timeRemaining;
		// timer = time / 60 + " : " + time % 60;

		driveBaseCurrentDraw = RobotMap.m_robotContainer.m_driveBaseSubsystem.getCurrentDraw();
	}

	public void initSendable(SendableBuilder builder){

	}

}
