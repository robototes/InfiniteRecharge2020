package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.units.Distance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.constants.ClimbConstants;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbHeight;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class ClimbMotorSubsystem extends SubsystemBase implements Loggable {

	@Log.ToString(tabName = "Climb")
	public Distance m_currentClimbHeight;

	@Log.NumberBar(min = -1, max = 1, name = "Left Climb Speed", tabName = "Climb", methodName = "get")
	private CANSparkMax m_leftClimbMotor;
	@Log.NumberBar(min = -1, max = 1, name = "Right Climb Speed", tabName = "Climb", methodName = "get")
	private CANSparkMax m_rightClimbMotor;

	private CANEncoder m_encoder;
	private CANEncoder m_leftEncoder;

	private CANPIDController m_pidController;

	@Log.ToString(tabName = "Climb")
	private ClimbHeight reference;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;

		m_pidController = m_rightClimbMotor.getPIDController();

		m_encoder = m_rightClimbMotor.getEncoder();
		m_leftEncoder = m_leftClimbMotor.getEncoder();
		m_pidController.setP(0.005);
	}

	public boolean atReference() {
		return Math.abs(ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES
				.calculateReverseRatio(m_currentClimbHeight.subtract(reference.value))) > ClimbConstants.DEADBAND;
	}

	public void climbExtendArm() {
		m_rightClimbMotor.set(ClimbConstants.MAX_SPEED);
		RobotState.m_climbState = RobotState.ClimbState.CLIMBING;
	}

	public void climbRetractArm() {
		m_rightClimbMotor.set(-ClimbConstants.MAX_SPEED);
		RobotState.m_climbState = RobotState.ClimbState.CLIMBING;
	}

	public void climbStop() {
		m_rightClimbMotor.set(0);
		RobotState.m_climbState = RobotState.ClimbState.NOT_CLIMBING;
	}

	public double getEncoderValue() {
		return m_encoder.getPosition();
	}

	@Override
	public void periodic() {
		double heightFromOffset = ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES.calculateRatio(getEncoderValue());
		m_currentClimbHeight = new Distance(heightFromOffset).add(ClimbConstants.CLIMB_OFFSET_HEIGHT);
	}

	@Config.NumberSlider
	public void setMotors(double value) {
		// left : 0-75
		// right: -75 - 0
		if (value < 0 && m_encoder.getPosition() < 0 || value > 0 && m_encoder.getPosition() > -76) {
			m_rightClimbMotor.set(-value);
		} else {
			m_rightClimbMotor.set(0);
		}

		if (value < 0 && m_leftEncoder.getPosition() > 0 || value > 0 && m_leftEncoder.getPosition() < 76) {
			m_leftClimbMotor.set(value);
		} else {
			m_leftClimbMotor.set(0);
		}
		System.out.println("right: " + m_encoder.getPosition());
		System.out.println("left: " + m_leftEncoder.getPosition());
		RobotState.m_climbState = RobotState.ClimbState.CLIMBING;
	}

	public void setReference(ClimbHeight newHeight) {
		this.reference = newHeight;
		Distance travelFromOffset = newHeight.value.subtract(ClimbConstants.CLIMB_OFFSET_HEIGHT);
		double wantedRotations = ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES.calculateReverseRatio(travelFromOffset);

		m_pidController.setReference(wantedRotations, ControlType.kPosition);
		RobotState.m_climbState = RobotState.ClimbState.CLIMBING;

	}

	public double getCurrentDraw() {
		return m_leftClimbMotor.getOutputCurrent() + m_rightClimbMotor.getOutputCurrent();
	}

}
