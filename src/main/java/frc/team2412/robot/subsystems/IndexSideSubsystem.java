package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.Loggable;

public class IndexSideSubsystem extends SubsystemBase implements Loggable, IndexInterface {

	private CANSparkMax m_motor;
	private CANEncoder m_encoder;
	private CANPIDController m_pidEncoder;

	public IndexSideSubsystem(CANSparkMax motor) {
		m_motor = motor;
		m_encoder = m_motor.getEncoder();
		m_pidEncoder = m_motor.getPIDController();
	}

	@Override
	public void set(double val) {
		m_motor.set(val);
	}

	@Override
	public void up() {
		set(IndexerConstants.MOTOR_IN_SPEED);
	}

	@Override
	public void out() {
		set(IndexerConstants.MOTOR_OUT_SPEED);
	}

	@Override
	public void off() {
		set(IndexerConstants.MOTOR_OFF_SPEED);
	}

	@Override
	public void pid(double val) {
		m_pidEncoder.setP(val);
	}

	@Override
	public double getRevolutions() {
		return m_encoder.getPosition();
	}

	@Override
	public double getCurrentDraw() {
		return m_motor.getOutputCurrent();
	}
	

}
