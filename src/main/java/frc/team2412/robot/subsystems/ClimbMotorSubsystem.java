package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.units.Distance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ClimbConstants;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbHeight;

public class ClimbMotorSubsystem extends SubsystemBase {
	// private WPI_TalonSRX m_ClimbMotorSubsystem;

	public Distance m_currentClimbHeight;
	private CANEncoder m_encoder;
	private CANSparkMax m_leftClimbMotor;

	private CANPIDController m_pidController;

	private CANSparkMax m_rightClimbMotor;
	private ClimbHeight reference;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;
		m_leftClimbMotor.follow(rightClimbMotor);

		m_pidController = m_rightClimbMotor.getPIDController();
		m_encoder = m_rightClimbMotor.getEncoder();
		m_pidController.setP(0.005);
	}

	public boolean atReference() {
		return Math.abs(ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES
				.calculateReverseRatio(m_currentClimbHeight.subtract(reference.value))) > ClimbConstants.DEADBAND;
	}

	public void climbExtendArm() {
		m_rightClimbMotor.set(ClimbConstants.MAX_SPEED);
	}

	public void climbRetractArm() {
		m_rightClimbMotor.set(-ClimbConstants.MAX_SPEED);
	}

	public void climbStop() {
		m_rightClimbMotor.set(0);
	}

	public double getEncoderValue() {
		return m_encoder.getPosition();
	}

	@Override
	public void periodic() {
		double heightFromOffset = ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES.calculateRatio(getEncoderValue());
		m_currentClimbHeight = new Distance(heightFromOffset).add(ClimbConstants.CLIMB_OFFSET_HEIGHT);
	}

	public void setMotors(double value) {
		m_rightClimbMotor.set(value);
	}

	public void setReference(ClimbHeight newHeight) {
		this.reference = newHeight;
		Distance travelFromOffset = newHeight.value.subtract(ClimbConstants.CLIMB_OFFSET_HEIGHT);
		double wantedRotations = ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES.calculateReverseRatio(travelFromOffset);

		m_pidController.setReference(wantedRotations, ControlType.kPosition);
	}

	public double getCurrentDraw() {
		return m_leftClimbMotor.getOutputCurrent() + m_rightClimbMotor.getOutputCurrent();
	}

}
