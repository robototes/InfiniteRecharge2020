package frc.team2412.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.FlywheelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;

import static frc.team2412.robot.Subsystems.constants.FlywheelConstants.*;

public class FlywheelSubsystem extends SubsystemBase implements Loggable {
	
	

	private CANSparkMax m_flywheelMotor1;
	private CANSparkMax m_flywheelMotor2;

	private CANEncoder m_encoder;
	private CANPIDController m_pidcontroller;

	public FlywheelSubsystem(CANSparkMax flywheelMotor1, CANSparkMax flywheelMotor2) {
		m_flywheelMotor1 = flywheelMotor1;
		m_flywheelMotor2 = flywheelMotor2;

		m_flywheelMotor2.setInverted(true);
		m_flywheelMotor2.follow(flywheelMotor1);

		m_encoder = flywheelMotor1.getEncoder();
		m_pidcontroller = flywheelMotor1.getPIDController();
		
		m_pidcontroller.setP(FlywheelConstants.P);
		m_pidcontroller.setP(FlywheelConstants.I);
		m_pidcontroller.setP(FlywheelConstants.D);

	
	}

	public void shoot() {
		m_flywheelMotor1.set(1.0);
	}

	public void stop() {
		m_flywheelMotor1.set(0.0);
	}

	public void setRPM(double wantedVelocity) {
		double wantedRPM = NEO_RPM_TO_FLYWHEEL_MPS.calculateReverseRatio(wantedVelocity);

		m_pidcontroller.setReference(wantedRPM, ControlType.kVelocity);
	}
	
	@Config(defaultValueNumeric = FlywheelConstants.P, name = "Set Flywheel P")
	public void setP(double newP) {
		m_pidcontroller.setP(newP);
	}
	
	@Config(defaultValueNumeric = FlywheelConstants.I, name = "Set Flywheel I")
	public void setI(double newI) {
		m_pidcontroller.setI(newI);
	}
	
	@Config(defaultValueNumeric = FlywheelConstants.D, name = "Set Flywheel D")
	public void setD(double newD) {
		m_pidcontroller.setP(newD);
	}
}
