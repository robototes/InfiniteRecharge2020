package frc.team2412.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class ClimbMotorSubsystem extends SubsystemBase{

	private WPI_TalonSRX m_ClimbMotorSubsystem;
	
	private WPI_TalonSRX leftMotor = RobotMap.leftClimbMotor;
	private WPI_TalonSRX rightMotor = RobotMap.rightClimbMotor;
	
	public ClimbMotorSubsystem(WPI_TalonSRX leftClimbMotor, WPI_TalonSRX rightClimbMotor) {
		m_leftMotor = leftMotor;
		m_rightMotor = rightMotor;
		
	}

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		m_ClimbMotorSubsystem.ClimbRails();
	}
	
}
