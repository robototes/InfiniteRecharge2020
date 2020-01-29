package frc.team2412.robot.Subsystems.constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;

public class ClimbUpAndDown extends ClimbLiftSubsystem{
	public ClimbUpAndDown(WPI_TalonSRX leftClimbMotor, WPI_TalonSRX rightClimbMotor, boolean climbMode) {
		super(leftClimbMotor, rightClimbMotor);
		// TODO Auto-generated constructor stub
	}

	private WPI_TalonSRX left = RobotMap.leftClimbMotor;
	private WPI_TalonSRX right = RobotMap.rightClimbMotor;
	
	public void climbUp()
	{
		left.set(1.0);
		right.set(1.0);	
	}
	
	public void pullUp()
	{
		left.set(-1.0);
		right.set(-1.0);
	}
	
	public void climbStop() {
		left.set(0.0);
		right.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		m_ClimbLiftSubsystem.ClimbLiftSubsystem();
	}
}
