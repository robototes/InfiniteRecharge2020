package frc.team2412.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.ClimbPneumatics;


public class ClimbLiftSubsystem extends SubsystemBase{
	
	//For Pneumatics
	
	private DoubleSolenoid m_leftPneumatic;
	private DoubleSolenoid m_rightPneumatic;
	
	public static boolean climbMode = false;
	
	public ClimbLiftSubsystem(DoubleSolenoid leftPneumatic, DoubleSolenoid rightPneumatic) {
//		if(climbMode==true) {
//			RetractRails(leftPneumatic, rightPneumatic);
//			climbMode = false;
//		}
//		else {
//			DeployRails(leftPneumatic, rightPneumatic);
//			climbMode = true;
//		}
		
		m_leftPneumatic = leftPneumatic;
		m_rightPneumatic = rightPneumatic;
		if(climbMode == false) {
			DeployRails();
			}
		else { 
			RetractRails();
		}
	}
			

		public void DeployRails() {
			m_leftPneumatic.set(DoubleSolenoid.Value.kForward);
			m_rightPneumatic.set(DoubleSolenoid.Value.kForward);
			System.out.println("Deploy");
		}

		public void RetractRails() {
			m_leftPneumatic.set(DoubleSolenoid.Value.kReverse);
			m_rightPneumatic.set(DoubleSolenoid.Value.kReverse);
			System.out.println("Retract");
		}
		


	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	

}
