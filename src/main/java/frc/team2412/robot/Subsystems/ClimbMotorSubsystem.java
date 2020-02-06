package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class ClimbMotorSubsystem extends SubsystemBase {
	// private WPI_TalonSRX m_ClimbMotorSubsystem;

	private CANSparkMax m_leftClimbMotor = RobotMap.leftClimbMotor;
	private CANSparkMax m_rightClimbMotor = RobotMap.rightClimbMotor;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;
	}

public void climbUp() {
		m_leftClimbMotor.set(1.0);
		m_rightClimbMotor.set(1.0);
		System.out.println("Extending");
	}

	public void pullUp() {
		m_leftClimbMotor.set(-1.0);
		m_rightClimbMotor.set(-1.0);
		System.out.println("Retracting");
	}

	public void climbStop() {
		m_leftClimbMotor.set(0.0);
		m_rightClimbMotor.set(0.0);
		System.out.println("Stopped");
	}

}
