package frc.team2412.robot;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.RobotController;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class RobotState implements Loggable {

	private RobotContainer m_robotContainer;

	@Log.ToString(tabName = "Robot State")
	public static UnbalancedSide m_unbalancedSide;

	@Log.Dial(min = 0, max = 5, showValue = true, name = "Power Cell Count", tabName = "Robot State")
	public static int m_ballCount = 0;

	@Log.ToString(tabName = "Robot State")
	public static IntakeDirection m_intakeDirection = IntakeDirection.NONE;

	@Log.ToString(tabName = "Robot State")
	public static ClimbState m_climbState = ClimbState.NOT_CLIMBING;

	@Log.ToString(tabName = "Robot State")
	public static LiftState m_liftSolenoidState = LiftState.WITHDRAWN;

	@Log.ToString(tabName = "Robot State")
	public static GearboxState m_gearState = GearboxState.LOW;

	@Log.ToString(tabName = "Robot State")
	public static BrownoutStage m_brownoutStage = BrownoutStage.ZERO;

	@Config.ToggleSwitch(tabName = "Robot State")
	public static boolean sixBallAuto = true;

	@Config.ToggleSwitch(tabName = "Robot State")
	public static boolean threeBallAuto = false;

	@Config.ToggleSwitch(tabName = "Robot State")
	public static boolean justMoveAuto = true;

	@Log(tabName = "Misc.")
	public static String eventName = "N/A";

	@Log.ToString(tabName = "Misc.")
	public static MatchType matchType = MatchType.None;

	@Log(tabName = "Misc.")
	public static int matchNumber = 0;

	@Log.ToString(tabName = "Misc.")
	public static Alliance alliance = Alliance.Invalid;

	@Log(tabName = "Misc.")
	public static int location = 0;

	public RobotState(RobotContainer robotContainer) {
		m_robotContainer = robotContainer;
	}

	public static enum UnbalancedSide {
		FRONT, BACK;

		@Override
		public String toString() {
			if (this.equals(FRONT)) {
				return "Front";
			} else {
				return "Back";
			}
		}
	}

	public static enum IntakeDirection {
		NONE, FRONT, BACK, BOTH;

		@Override
		public String toString() {
			if (this.equals(FRONT)) {
				return "Front";
			} else if (this.equals(BACK)) {
				return "Back";
			} else if (this.equals(BOTH)) {
				return "Both";
			} else {
				return "None";
			}
		}
	}

	public static enum ClimbState {
		CLIMBING, NOT_CLIMBING;

		@Override
		public String toString() {
			if (this.equals(NOT_CLIMBING)) {
				return "Not Climbing, woohoo";
			} else {
				return "We climbin boiz";
			}
		}
	}

	public static enum LiftState {
		WITHDRAWN, EXTENDED;

		@Override
		public String toString() {
			if (this.equals(WITHDRAWN)) {
				return "Withdrawn";
			} else {
				return "Extended";
			}
		}
	}

	public static enum GearboxState {
		HIGH, LOW;
		@Override
		public String toString() {
			if (this.equals(HIGH)) {
				return "High";
			} else {
				return "Low";
			}
		}
	}

	public static UnbalancedSide flip(UnbalancedSide s) {
		switch (s) {
		case FRONT:
			return UnbalancedSide.BACK;
		default:
			return UnbalancedSide.FRONT;
		}

	}

	public static int getBallCount() {
		return m_ballCount;
	}

	public static void setBallCount(int m_ballCount) {
		RobotState.m_ballCount = m_ballCount;
	}

	public static void addBall() {
		m_ballCount += 1;
		if (m_ballCount > 5) {
			System.out.println("Too many balls!");
		}
	}

	public static boolean hasFiveBalls() {
		return m_ballCount == 5;
	}

	public static void removeBall() {
		m_ballCount -= 1;
		if (m_ballCount < 0) {
			m_ballCount = 0;
			System.out.println("Removed non-existant ball");
		}
	}

	public static IntakeDirection getintakeDirection() {
		return m_intakeDirection;
	}

	public static void setintakeDirection(IntakeDirection m_intakeDirection) {
		RobotState.m_intakeDirection = m_intakeDirection;
	}

	public static ClimbState getclimbState() {
		return m_climbState;
	}

	public static void setclimbState(ClimbState m_climbState) {
		RobotState.m_climbState = m_climbState;
	}

	public static LiftState getliftSolenoidState() {
		return m_liftSolenoidState;
	}

	public static void setliftSolenoidState(LiftState m_liftSolenoidState) {
		RobotState.m_liftSolenoidState = m_liftSolenoidState;
	}

	public double getTotalCurrentDraw() {
		double currentDraw = (RobotMap.CLIMB_CONNECTED ? m_robotContainer.m_climbMotorSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.DRIVE_BASE_CONNECTED ? m_robotContainer.m_driveBaseSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.SHOOTER_CONNECTED ? m_robotContainer.m_flywheelSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.INDEX_CONNECTED ? m_robotContainer.m_indexerMotorSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.INTAKE_CONNECTED ? m_robotContainer.m_intakeMotorOnOffSubsystem.getCurrentDraw() : 0)
				+ (RobotMap.SHOOTER_CONNECTED ? m_robotContainer.m_turretSubsystem.getCurrentDraw() : 0)
				+ RobotMap.compressor.getCompressorCurrent();

		return currentDraw;
	}

	public static enum BrownoutStage {
		ZERO(1), ONE(0.75), TWO(0.5), THREE(0);

		double value;

		private BrownoutStage(double value) {
			this.value = value;
		}

	}

	public void brownoutWarning() {
		double voltage = RobotController.getInputVoltage();

		if (voltage < 7) {
			m_brownoutStage = BrownoutStage.ONE;
		} else if (voltage < 6) {
			m_brownoutStage = BrownoutStage.TWO;
		} else if (voltage < 5) {
			m_brownoutStage = BrownoutStage.THREE;
		} else {
			m_brownoutStage = BrownoutStage.ZERO;
		}

	}

}
