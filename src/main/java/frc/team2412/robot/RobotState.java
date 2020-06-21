package frc.team2412.robot;

import static frc.team2412.robot.RobotMapConstants.CLIMB_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.DRIVE_BASE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INDEX_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INTAKE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.SHOOTER_CONNECTED;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.RobotController;
import frc.team2412.robot.subsystems.constants.LiftConstants.LiftState;
import io.github.oblarg.oblog.Loggable;

public class RobotState {

	private final RobotContainer m_robotContainer;

	public static UnbalancedSide m_unbalancedSide = UnbalancedSide.FRONT;

	public static int m_ballCount = 0;

	public static IntakeDirection m_intakeDirection = IntakeDirection.NONE;

	public static ClimbState m_climbState = ClimbState.NOT_CLIMBING;

	public static LiftState m_liftSolenoidState = LiftState.DOWN;

	public static GearboxState m_gearState = GearboxState.LOW;

	public static BrownoutStage m_brownoutStage = BrownoutStage.ZERO;

	public static boolean sixBallAuto = true;

	public static boolean threeBallAuto = false;

	public static boolean justMoveAuto = true;

	public static String eventName = "N/A";

	public static MatchType matchType = MatchType.None;

	public static int matchNumber = 0;

	public static Alliance alliance = Alliance.Invalid;

	public static int location = 0;

	public RobotState(RobotContainer robotContainer) {
		m_robotContainer = robotContainer;
	}

	public enum UnbalancedSide {
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

	public enum IntakeDirection {
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

	public enum ClimbState {
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

	public enum GearboxState {
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
		return s == UnbalancedSide.FRONT ? UnbalancedSide.BACK : s;
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

	public static void setClimbState(ClimbState m_climbState) {
		RobotState.m_climbState = m_climbState;
	}

	public static LiftState getliftSolenoidState() {
		return m_liftSolenoidState;
	}

	public static void setliftSolenoidState(LiftState m_liftSolenoidState) {
		RobotState.m_liftSolenoidState = m_liftSolenoidState;
	}

	public double getTotalCurrentDraw() {

		return (CLIMB_CONNECTED ? m_robotContainer.m_climbMotorSubsystem.getCurrentDraw() : 0)
				+ (DRIVE_BASE_CONNECTED ? m_robotContainer.m_driveBaseSubsystem.getCurrentDraw() : 0)
				+ (SHOOTER_CONNECTED ? m_robotContainer.m_flywheelSubsystem.getCurrentDraw() : 0)
				+ (INDEX_CONNECTED ? m_robotContainer.m_indexerMotorSubsystem.getCurrentDraw() : 0)
				+ (INTAKE_CONNECTED ? m_robotContainer.m_intakeMotorOnOffSubsystem.getCurrentDraw() : 0)
				+ (SHOOTER_CONNECTED ? m_robotContainer.m_turretSubsystem.getCurrentDraw() : 0)
				+ RobotMap.compressor.getCompressorCurrent();
	}

	public enum BrownoutStage {
		ZERO(1), ONE(0.75), TWO(0.5), THREE(0);

		double value;

		BrownoutStage(double value) {
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
