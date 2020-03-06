package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;

public class IndexSideSubsystem extends SubsystemBase implements Loggable, IndexInterface{

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
		set();
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pid(double val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRevolutions() {
		// TODO Auto-generated method stub
		
	}

}
