package frc.team2412.robot;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class RobotState implements Loggable {

	@Log
	public static int m_ballCount = 0;

	@Log
	public static IntakeDirection m_intakeDirection = IntakeDirection.NONE;

	@Log
	public static ClimbState m_climbState = ClimbState.NOT_CLIMBING;

	@Log
	public static LiftState m_liftSolenoidState = LiftState.WITHDRAWN;

	@Log
	public static GearState m_gearState = GearState.LOW;

	public enum IntakeDirection {
		NONE, FRONT, BACK, BOTH;
	}

	public enum ClimbState {
		CLIMBING, NOT_CLIMBING;
	}

	public enum LiftState {
		WITHDRAWN, EXTENDED;
	}

	public static enum GearState {
		HIGH, LOW;
	}

	public RobotState() {

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

	public IntakeDirection getintakeDirection() {
		return m_intakeDirection;
	}

	public void setintakeDirection(IntakeDirection m_intakeDirection) {
		RobotState.m_intakeDirection = m_intakeDirection;
	}

	public ClimbState getclimbState() {
		return m_climbState;
	}

	public void setclimbState(ClimbState m_climbState) {
		RobotState.m_climbState = m_climbState;
	}

	public LiftState getliftSolenoidState() {
		return m_liftSolenoidState;
	}

	public void setliftSolenoidState(LiftState m_liftSolenoidState) {
		RobotState.m_liftSolenoidState = m_liftSolenoidState;
	}

}
