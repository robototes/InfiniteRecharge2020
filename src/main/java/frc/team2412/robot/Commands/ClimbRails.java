package frc.team2412.robot.Commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

public class ClimbRails extends CommandBase{
	private WPI_TalonSRX m_leftClimbMotor;
	private WPI_TalonSRX m_rightClimbMotor;
	
	public ClimbRails(WPI_TalonSRX leftClimbMotor, WPI_TalonSRX rightClimbMotor) {
		super();
		
	}

	public ClimbRails(ClimbMotorSubsystem m_ClimbMotorSubsystem) {
		// TODO Auto-generated constructor stub
	}

	private WPI_TalonSRX leftClimbMotor = RobotMap.leftClimbMotor;
	private WPI_TalonSRX rightClimbMotor = RobotMap.rightClimbMotor;
	
	public void climbUp()
	{
		leftClimbMotor.set(1.0);
		rightClimbMotor.set(1.0);
		System.out.println("Extending");
	}
	
	public void pullUp()
	{
		leftClimbMotor.set(-1.0);
		rightClimbMotor.set(-1.0);
		System.out.println("Retracting");
	}
	
	public void climbStop() {
		leftClimbMotor.set(0.0);
		rightClimbMotor.set(0.0);
		System.out.println("Stopped");
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		m_ClimbLiftSubsystem.ClimbMotorSubsystem();
	}
}
