package frc.team2412.robot.Commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

<<<<<<< HEAD
public class ClimbRails extends CommandBase {
	//private CANSparkMax m_leftClimbMotor;
	//private CANSparkMax m_rightClimbMotor;
=======
public class ClimbRails extends SubsystemBase {
	private WPI_TalonSRX m_leftClimbMotor;
	private WPI_TalonSRX m_rightClimbMotor;
>>>>>>> branch 'master' of https://github.com/AriGlockner/InfiniteRecharge2020-1.git

<<<<<<< HEAD
	/*public ClimbRails(CANSparkMax leftClimbMotor, CANSparkMax rightClimbMotor) {
		super();

=======
	public ClimbRails(WPI_TalonSRX leftClimbMotor, WPI_TalonSRX rightClimbMotor) {
		m_leftClimbMotor = leftClimbMotor;
		m_rightClimbMotor = rightClimbMotor;
>>>>>>> branch 'master' of https://github.com/AriGlockner/InfiniteRecharge2020-1.git
	}

<<<<<<< HEAD
	public ClimbRails(ClimbMotorSubsystem m_ClimbMotorSubsystem) {
		// TODO Auto-generated constructor stub
	}
	
	private CANSparkMax leftClimbMotor = RobotMap.leftClimbMotor;
	private CANSparkMax rightClimbMotor = RobotMap.rightClimbMotor;

=======
>>>>>>> branch 'master' of https://github.com/AriGlockner/InfiniteRecharge2020-1.git
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
<<<<<<< HEAD

	/*@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		m_leftClimbMotor.ClimbMotorSubsystem();
		m_rightClimbMotor.ClimbMotorSubsystem();
	}*/
	
=======
>>>>>>> branch 'master' of https://github.com/AriGlockner/InfiniteRecharge2020-1.git
}
