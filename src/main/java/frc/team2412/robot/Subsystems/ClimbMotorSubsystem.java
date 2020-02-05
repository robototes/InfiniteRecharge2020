package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.constants.ClimbConstants;

public class ClimbMotorSubsystem extends SubsystemBase {
	// private WPI_TalonSRX m_ClimbMotorSubsystem;

	private CANSparkMax m_leftClimbMotor = RobotMap.leftClimbMotor;
	private CANSparkMax m_rightClimbMotor = RobotMap.rightClimbMotor;

	private SpeedControllerGroup m_climbLiftMotors;

	public ClimbMotorSubsystem(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;

		m_climbLiftMotors = new SpeedControllerGroup(m_leftClimbMotor, m_rightClimbMotor);
	}

	public void climbExtendArm() {
		m_climbLiftMotors.set(ClimbConstants.mAX_SPEED);
	}

	public void climbRetractArm() {
		m_climbLiftMotors.set(-ClimbConstants.mAX_SPEED);
	}

	public void climbStop() {
		m_climbLiftMotors.set(0);
	}

}
