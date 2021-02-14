package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class IndexerMotorFrontSubsystem extends IndexerMotorSideSubsystem {

	public IndexerMotorFrontSubsystem(CANSparkMax motor) {
		super(motor, IndexMotorSubsystemType.FRONT);
	}

	@Override
	public CANEncoder getEncoder() {
		return super.getEncoder();
	}

	@Override
	public CANSparkMax getMainMotor() {
		return super.getMainMotor();
	}

	@Override
	public void set(double speed) {
		super.set(speed);
	}
}
