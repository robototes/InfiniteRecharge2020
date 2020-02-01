package frc.team2412.robot.Commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

public class ClimbRails extends CommandBase {
	//private CANSparkMax m_leftClimbMotor;
	//private CANSparkMax m_rightClimbMotor;

	/*public ClimbRails(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		super();

	}

	public ClimbRails(ClimbMotorSubsystem m_ClimbMotorSubsystem) {
		// TODO Auto-generated constructor stub
	}
	
	private CANSparkMax leftClimbMotor = RobotMap.leftClimbMotor;
	private CANSparkMax rightClimbMotor = RobotMap.rightClimbMotor;

	public void climbUp() {
		leftClimbMotor.set(1.0);
		rightClimbMotor.set(1.0);
		System.out.println("Extending");
	}

	public void pullUp() {
		leftClimbMotor.set(-1.0);
		rightClimbMotor.set(-1.0);
		System.out.println("Retracting");
	}

	public void climbStop() {
		leftClimbMotor.set(0.0);
		rightClimbMotor.set(0.0);
		System.out.println("Stopped");
	}

	/*@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		m_leftClimbMotor.ClimbMotorSubsystem();
		m_rightClimbMotor.ClimbMotorSubsystem();
	}*/
	
}
