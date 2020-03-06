package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import io.github.oblarg.oblog.annotations.Log;

public class IndexerMotorBackSubsystem extends IndexerMotorSideSubsystem {

	public IndexerMotorBackSubsystem(CANSparkMax motor) {
		super(motor, IndexMotorSubsystemType.BACK);
	}

	@Override
	@Log.ToString(methodName = "getPosition", name = "Current Back Motor Position", tabName = "Index")
	public CANEncoder getEncoder() {
		return super.getEncoder();
	}

	@Override
	@Log.NumberBar(min = -1, max = 1, methodName = "get", name = "Current Back Motor Speed", tabName = "Index")
	public CANSparkMax getMainMotor() {
		return super.getMainMotor();
	}
}
