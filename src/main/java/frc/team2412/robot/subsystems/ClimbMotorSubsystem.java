package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.ClimbConstants.CLIMB_OFFSET_HEIGHT;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.DEADBAND;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MAX_ARM_EXTENSION;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MAX_SPEED;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MIN_ARM_EXTENSION;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.units.Distance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.ClimbState;
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

	private CANEncoder m_rightEncoder;
	private CANEncoder m_leftEncoder;

	private CANPIDController m_pidController;

	@Log.ToString(tabName = "Climb")
	private ClimbHeight reference;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;

		m_rightClimbMotor.setInverted(true);

		m_pidController = m_rightClimbMotor.getPIDController();

		m_rightEncoder = m_rightClimbMotor.getEncoder();
		m_rightEncoder.setInverted(true);

		m_leftEncoder = m_leftClimbMotor.getEncoder();
		m_pidController.setP(0.005);
	}

	public boolean atReference() {
		return Math.abs(MOTOR_REVOLUTIONS_TO_INCHES
				.calculateReverseRatio(m_currentClimbHeight.subtract(reference.value))) > DEADBAND;
	}

	public void climbExtendArm() {
		setMotors(MAX_SPEED);
	}

	public void climbRetractArm() {
		setMotors(-MAX_SPEED);
	}

	public void climbStop() {
		setMotors(0);
	}

	public double getEncoderValue() {
		return m_rightEncoder.getPosition();
	}

	@Override
	public void periodic() {
		double heightFromOffset = MOTOR_REVOLUTIONS_TO_INCHES.calculateRatio(getEncoderValue());
		m_currentClimbHeight = new Distance(heightFromOffset).add(CLIMB_OFFSET_HEIGHT);
	}

	@Config.NumberSlider
	public void setMotors(double value) {
		// left : 0-75
		// right: -75 - 0
		setMotor(value, m_rightClimbMotor, m_rightEncoder);
		setMotor(value, m_leftClimbMotor, m_leftEncoder);

		RobotState.m_climbState = m_rightClimbMotor.get() != 0 || m_leftClimbMotor.get() != 0 ? ClimbState.CLIMBING
				: ClimbState.NOT_CLIMBING;
	}

	private void setMotor(double value, CANSparkMax motor, CANEncoder encoder) {
		if (value < 0 && MIN_ARM_EXTENSION < encoder.getPosition()
				|| 0 < value && encoder.getPosition() < MAX_ARM_EXTENSION) {
			motor.set(value);
		} else {
			motor.set(0);
		}
	}

	public void setReference(ClimbHeight newHeight) {
		reference = newHeight;
		Distance travelFromOffset = newHeight.value.subtract(CLIMB_OFFSET_HEIGHT);
		double wantedRotations = MOTOR_REVOLUTIONS_TO_INCHES.calculateReverseRatio(travelFromOffset);

		m_pidController.setReference(wantedRotations, ControlType.kPosition);
		RobotState.m_climbState = RobotState.ClimbState.CLIMBING;

	}

	public double getCurrentDraw() {
		return m_leftClimbMotor.getOutputCurrent() + m_rightClimbMotor.getOutputCurrent();
	}

}
