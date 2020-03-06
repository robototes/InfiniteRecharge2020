package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;

public class IndexerLiftMotorSubsystem extends SubsystemBase implements Loggable{
	
	private CANSparkMax m_indexLeftLiftMotor, m_indexRightLiftMotor;
	
	public IndexerLiftMotorSubsystem(CANSparkMax indexLiftMotorRight, CANSparkMax indexLiftMotorLeft) {
		m_indexLeftLiftMotor = indexLiftMotorLeft;
		m_indexRightLiftMotor = indexLiftMotorRight;
	}
	
	public void setMotorSpeed(double speed) {
		m_indexLeftLiftMotor.set(speed);
		m_indexRightLiftMotor.set(speed);
	}
	
	public CANSparkMax getLiftLeftMotor() {
		return m_indexLeftLiftMotor;
	}
	public CANSparkMax getLiftRightMotor() {
		return m_indexRightLiftMotor;
	}
	
}
