package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerMotorLiftSubsystem extends SubsystemBase implements IIndexMotorSubsystem {

	@Log.ToString(methodName = "getPosition", name = "Current Lift Motor Position", tabName = "Index")
	CANEncoder m_encoder;
	@Log.Exclude
	CANSparkMax m_leftLiftMotor;
	@Log.Exclude
	CANPIDController m_pidController;
	@Log.NumberBar(min = -1, max = 1, methodName = "get", name = "Current Lift Motor Speed", tabName = "Index")
	CANSparkMax m_rightLiftMotor;

	public IndexerMotorLiftSubsystem(CANSparkMax rightLiftMotor, CANSparkMax leftLiftMotor) {
		this.m_rightLiftMotor = rightLiftMotor;
		this.m_leftLiftMotor = leftLiftMotor;

		this.m_pidController = rightLiftMotor.getPIDController();
		this.m_encoder = rightLiftMotor.getEncoder();

		leftLiftMotor.follow(rightLiftMotor);

		configPID(m_pidController);
	}

	@Override
	@Config.NumberSlider(min = -1, max = 1, name = "Set Lift Motor Speed")
	public void set(double speed) {
		double newSpeed = MathUtils.constrain(speed, -getType().SPEED, getType().SPEED);
		m_rightLiftMotor.set(newSpeed);
	}

	@Override
	public double getCurrentDraw() {
		return m_leftLiftMotor.getOutputCurrent() + m_rightLiftMotor.getOutputCurrent();
	}

	@Override
	public CANEncoder getEncoder() {
		return m_encoder;
	}

	@Override
	public CANSparkMax getMainMotor() {
		return m_rightLiftMotor;
	}

	@Override
	public CANPIDController getPIDController() {
		return m_pidController;
	}

	@Override
	public IndexMotorSubsystemType getType() {
		return IndexMotorSubsystemType.LIFT;
	}

}
