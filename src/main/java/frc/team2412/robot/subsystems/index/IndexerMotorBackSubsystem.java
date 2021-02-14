package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class IndexerMotorBackSubsystem extends IndexerMotorSideSubsystem {

	public IndexerMotorBackSubsystem(CANSparkMax motor) {
		super(motor, IndexMotorSubsystemType.BACK);
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
