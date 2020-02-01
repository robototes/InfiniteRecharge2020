package frc.team2412.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class ClimbMotorSubsystem extends SubsystemBase {
	// private WPI_TalonSRX m_ClimbMotorSubsystem;

	private CANSparkMax m_leftMotor;
	private CANSparkMax m_rightMotor;
	
	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftMotor = leftClimbMotor;
		m_rightMotor = rightClimbMotor;

	}

	

}
