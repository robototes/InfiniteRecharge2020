package frc.team2412.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.ClimbConstants;
import frc.team2412.robot.Subsystems.constants.ClimbConstants.ClimbHeight;

public class ClimbMotorSubsystem extends SubsystemBase {
	// private WPI_TalonSRX m_ClimbMotorSubsystem;

	private CANSparkMax m_leftClimbMotor;
	private CANSparkMax m_rightClimbMotor;

	private CANEncoder m_encoder;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;

		m_leftClimbMotor.follow(rightClimbMotor);

		m_encoder = m_rightClimbMotor.getEncoder();

	}

	public void climbExtendArm() {
		m_rightClimbMotor.set(ClimbConstants.MAX_SPEED);
	}

	public void climbRetractArm() {
		m_rightClimbMotor.set(-ClimbConstants.MAX_SPEED);
	}

	public void climbStop() {
		m_rightClimbMotor.set(0);
	}

	public double getEncoderValue() {
		return m_encoder.getPosition();
	}

	public void climbToHeight(ClimbHeight newHeight) {
		if (getEncoderValue() / ClimbConstants.inchesPerRevolution
				+ ClimbConstants.CLIMB_OFFSET_HEIGHT < newHeight.value) {
			climbExtendArm();
		} else if (getEncoderValue() / ClimbConstants.inchesPerRevolution
				+ ClimbConstants.CLIMB_OFFSET_HEIGHT > newHeight.value) {
			climbRetractArm();
		}
		
		climbStop();
	}

}
