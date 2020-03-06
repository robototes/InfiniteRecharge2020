package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerMidMotorSubsystem extends SubsystemBase implements Loggable {

	@Log
	private CANSparkMax m_midMotorMaster;
	@Log
	private CANSparkMax m_midMotorSlave;
	@Log.Encoder
	private CANEncoder m_encoder;

	public IndexerMidMotorSubsystem(CANSparkMax masterMotor, CANSparkMax slaveMotor) {
		this.m_midMotorMaster = masterMotor;
		this.m_midMotorSlave = slaveMotor;

		m_midMotorSlave.follow(m_midMotorMaster);

		m_encoder = m_midMotorMaster.getEncoder();

		setDefaultCommand(new InstantCommand(() -> setDown()));
	}

	public void setSpeed(double val) {
		m_midMotorMaster.set(val);
	}

	public void setUp() {
		setSpeed(IndexerConstants.MID_MOTOR_UP_MAX_SPEED);
	}

	public void setDown() {
		setSpeed(IndexerConstants.MID_MOTOR_DOWN_MAX_SPEED);
	}

	public void setOff() {
		setSpeed(0);
	}

	public double getMotorRevolutions() {
		return m_encoder.getPosition();
	}

	public double getCurrentDraw() {
		return m_midMotorMaster.getOutputCurrent() + m_midMotorSlave.getOutputCurrent();
	}

}
