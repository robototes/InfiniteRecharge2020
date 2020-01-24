package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotorOnOffSubsystem extends SubsystemBase {

	private CANSparkMax m_intakeMotor;
	
	
	public IntakeMotorOnOffSubsystem(CANSparkMax Motor) {
		this.m_intakeMotor = Motor;		
	}

	public void intakeOn() {
		m_intakeMotor.set(1);
	}

	public void intakeOff() {
		m_intakeMotor.set(0);
	}

}
