package frc.team2412.robot.Commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

public class ClimbRails extends SubsystemBase {
	private CANSparkMax m_leftClimbMotor;
	private CANSparkMax m_rightClimbMotor;

	public ClimbRails(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;
	}

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
}
