package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerMotorLiftSubsystem extends SubsystemBase implements IIndexMotorSubsystem {

	CANEncoder m_encoder;
	CANSparkMax m_leftLiftMotor;
	CANPIDController m_pidController;
	CANSparkMax m_rightLiftMotor;

	public IndexerMotorLiftSubsystem(CANSparkMax rightLiftMotor, CANSparkMax leftLiftMotor) {
		this.m_rightLiftMotor = rightLiftMotor;
		this.m_leftLiftMotor = leftLiftMotor;

		this.m_pidController = rightLiftMotor.getPIDController();
		this.m_encoder = rightLiftMotor.getEncoder();

		leftLiftMotor.follow(rightLiftMotor);

		configPID(m_pidController);
	}

	@Override
	public void set(double speed) {
		double newSpeed = MathUtils.constrain(speed, -getType().SPEED, getType().SPEED);
		m_rightLiftMotor.set(newSpeed);
	}

	@Override
	public double getCurrentDraw() {
		return m_leftLiftMotor.getOutputCurrent() + m_rightLiftMotor.getOutputCurrent();
	}

	@Override
	public CANEncoder getEncoder() {
		return m_encoder;
	}

	@Override
	public CANSparkMax getMainMotor() {
		return m_rightLiftMotor;
	}

	@Override
	public CANPIDController getPIDController() {
		return m_pidController;
	}

	@Override
	public IndexMotorSubsystemType getType() {
		return IndexMotorSubsystemType.LIFT;
	}

}
