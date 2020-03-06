package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import io.github.oblarg.oblog.annotations.Log;

public class IndexerMotorFrontSubsystem extends IndexerMotorSideSubsystem {

	public IndexerMotorFrontSubsystem(CANSparkMax motor) {
		super(motor, IndexMotorSubsystemType.FRONT);
	}

	@Override
	@Log.ToString(methodName = "getPosition", name = "Current Front Motor Position", tabName = "Index")
	public CANEncoder getEncoder() {
		return super.getEncoder();
	}

	@Override
	@Log.NumberBar(min = -1, max = 1, methodName = "get", name = "Current Front Motor Speed", tabName = "Index")
	public CANSparkMax getMainMotor() {
		return super.getMainMotor();
	}
}
