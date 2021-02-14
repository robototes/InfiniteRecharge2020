package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class IndexerMotorSideSubsystem extends SubsystemBase implements IIndexMotorSubsystem {
	private CANEncoder m_encoder;
	private CANSparkMax m_motor;
	private CANPIDController m_pidController;
	private final IndexMotorSubsystemType m_type;

	public IndexerMotorSideSubsystem(CANSparkMax motor, IndexMotorSubsystemType type) {
		this.m_type = type;
		this.m_motor = motor;
		this.m_encoder = motor.getEncoder();
		this.m_pidController = motor.getPIDController();

		this.configPID(m_pidController);
	}

	@Override
	public double getCurrentDraw() {
		return m_motor.getOutputCurrent();
	}

	@Override
	public CANEncoder getEncoder() {
		return m_encoder;
	}

	@Override
	public CANSparkMax getMainMotor() {
		return m_motor;
	}

	@Override
	public CANPIDController getPIDController() {
		return m_pidController;
	}

	@Override
	public IndexMotorSubsystemType getType() {
		return m_type;
	}

}
