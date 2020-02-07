package frc.team2412.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.FlywheelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

import static frc.team2412.robot.Subsystems.constants.FlywheelConstants.*;

public class FlywheelSubsystem extends SubsystemBase implements Loggable {

	private CANSparkMax m_flywheelLeftMotor;
	private CANSparkMax m_flywheelRightMotor;

	private CANEncoder m_encoder;
	private CANPIDController m_pidController;

	public FlywheelSubsystem(CANSparkMax flywheelLeftMotor, CANSparkMax flywheelRightMotor) {
		m_flywheelLeftMotor = flywheelLeftMotor;
		m_flywheelRightMotor = flywheelRightMotor;

		m_flywheelRightMotor.setInverted(true);
		m_flywheelRightMotor.follow(m_flywheelLeftMotor);

		m_encoder = m_flywheelLeftMotor.getEncoder();
		m_pidController = m_flywheelLeftMotor.getPIDController();

		m_pidController.setP(FlywheelConstants.P);
		m_pidController.setP(FlywheelConstants.I);
		m_pidController.setP(FlywheelConstants.D);

	}

	public void shoot() {
		m_flywheelLeftMotor.set(1.0);
	}

	public void stop() {
		m_flywheelLeftMotor.set(0.0);
	}

	public void setRPM(double wantedVelocity) {
		double wantedRPM = NEO_RPM_TO_FLYWHEEL_MPS.calculateReverseRatio(wantedVelocity);

		m_pidController.setReference(wantedRPM, ControlType.kVelocity);
	}

	@Log.NumberBar(min = 0, max = 30)
	public double currentSpeedInMetersPerSecond() {
		return NEO_RPM_TO_FLYWHEEL_MPS.calculateRatio(m_encoder.getVelocity());
	}

	@Config(defaultValueNumeric = FlywheelConstants.P, name = "Set Flywheel P")
	public void setP(double newP) {
		m_pidController.setP(newP);
	}

	@Config(defaultValueNumeric = FlywheelConstants.I, name = "Set Flywheel I")
	public void setI(double newI) {
		m_pidController.setI(newI);
	}

	@Config(defaultValueNumeric = FlywheelConstants.D, name = "Set Flywheel D")
	public void setD(double newD) {
		m_pidController.setP(newD);
	}
}
