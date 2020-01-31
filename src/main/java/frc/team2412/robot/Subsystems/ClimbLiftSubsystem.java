package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbLiftSubsystem extends SubsystemBase {

	// For Pneumatics

	private DoubleSolenoid m_leftPneumatic;
	private DoubleSolenoid m_rightPneumatic;

	public ClimbLiftSubsystem(DoubleSolenoid leftPneumatic, DoubleSolenoid rightPneumatic) {

		m_leftPneumatic = leftPneumatic;
		m_rightPneumatic = rightPneumatic;

	}

	public void DeployRails() {
		m_leftPneumatic.set(DoubleSolenoid.Value.kForward);
		m_rightPneumatic.set(DoubleSolenoid.Value.kForward);
		System.out.println("Deploying Rails");
	}

	public void RetractRails() {
		m_leftPneumatic.set(DoubleSolenoid.Value.kReverse);
		m_rightPneumatic.set(DoubleSolenoid.Value.kReverse);
		System.out.println("Retracting Rails");
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
