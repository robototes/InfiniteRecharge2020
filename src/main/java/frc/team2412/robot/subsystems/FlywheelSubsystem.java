package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.FlywheelConstants.NEO_RPM_TO_FLYWHEEL_MPS;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.constants.FlywheelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class FlywheelSubsystem extends SubsystemBase implements Loggable {

	@Log
	private CANSparkMax m_flywheelLeftMotor;
	@Log
	private CANSparkMax m_flywheelRightMotor;
	@Log.Encoder
	private CANEncoder m_leftEncoder;
	@Config.PIDController
	private CANPIDController m_pidLeftController;
	@Log.Encoder
	private CANEncoder m_rightEncoder;
	@Config.PIDController
	private CANPIDController m_pidRightController;

	public int brownoutStage;

	public FlywheelSubsystem(CANSparkMax flywheelLeftMotor, CANSparkMax flywheelRightMotor) {
		m_flywheelLeftMotor = flywheelLeftMotor;
		m_flywheelRightMotor = flywheelRightMotor;

		m_flywheelRightMotor.setInverted(true);
		System.out.println(m_flywheelRightMotor.getInverted());
		System.out.println(m_flywheelLeftMotor.getInverted());

		m_leftEncoder = m_flywheelLeftMotor.getEncoder();
		m_pidLeftController = m_flywheelLeftMotor.getPIDController();

		m_pidLeftController.setP(FlywheelConstants.P);
		m_pidLeftController.setI(FlywheelConstants.I);
		m_pidLeftController.setD(FlywheelConstants.D);
		m_pidLeftController.setFF(FlywheelConstants.FF);

		m_rightEncoder = m_flywheelRightMotor.getEncoder();
		m_pidRightController = m_flywheelRightMotor.getPIDController();

		m_pidRightController.setP(FlywheelConstants.P);
		m_pidRightController.setI(FlywheelConstants.I);
		m_pidRightController.setD(FlywheelConstants.D);
		m_pidRightController.setFF(FlywheelConstants.FF);

		m_flywheelLeftMotor.setIdleMode(IdleMode.kCoast);
		m_flywheelRightMotor.setIdleMode(IdleMode.kCoast);

	}

	@Config.NumberSlider(min = -1, max = 1, name = "Set speed", tabName = "Flywheel Subsystem", width = 3, height = 1, columnIndex = 2, rowIndex = 0)
	public void setSpeed(double speed) {
		System.out.println(speed);

		m_flywheelLeftMotor.set(speed);
		m_flywheelRightMotor.set(speed);
	}

	public void shoot() {
		m_flywheelLeftMotor.set(1.0);
	}

	public void stop() {
		m_flywheelLeftMotor.set(0.0);
	}

	public double getSpeed() {
		return m_flywheelLeftMotor.get();
	}

	@Config.NumberSlider(name = "Set Flywheel speed in Meters per Second", tabName = "Flywheel Subsystem", min = -100, max = 0, width = 3, height = 1, rowIndex = 1, columnIndex = 2)
	public void setRPMFromMPS(double wantedVelocity) {
		double wantedRPM = NEO_RPM_TO_FLYWHEEL_MPS.calculateRatio(wantedVelocity);

		if (brownoutStage == 1) {
			m_pidLeftController.setReference(wantedRPM * RobotState.Stage1Limitation, ControlType.kVelocity);
			m_pidRightController.setReference(wantedRPM * RobotState.Stage1Limitation, ControlType.kVelocity);
		} else if (brownoutStage == 2) {
			m_pidLeftController.setReference(wantedRPM * RobotState.Stage2Limitation, ControlType.kVelocity);
			m_pidRightController.setReference(wantedRPM * RobotState.Stage2Limitation, ControlType.kVelocity);
		} else if (brownoutStage == 3) {
			m_pidLeftController.setReference(wantedRPM * RobotState.Stage3Limitation, ControlType.kVelocity);
			m_pidRightController.setReference(wantedRPM * RobotState.Stage3Limitation, ControlType.kVelocity);
		} else {
			m_pidLeftController.setReference(wantedRPM, ControlType.kVelocity);
			m_pidRightController.setReference(wantedRPM, ControlType.kVelocity);
		}
	}

	@Log.NumberBar(min = 0, max = 30, name = "Left Flywheel Motor", tabName = "Flywheel Subsystem", width = 2, height = 1, rowIndex = 0, columnIndex = 0)
	public double currentLeftSpeedInMetersPerSecond() {
		return NEO_RPM_TO_FLYWHEEL_MPS.calculateReverseRatio(m_leftEncoder.getVelocity());
	}

	@Log.NumberBar(min = 0, max = 30, name = "Right Flywheel Motor", tabName = "Flywheel Subsystem", width = 2, height = 1, rowIndex = 1, columnIndex = 0)
	public double currentRightSpeedInMetersPerSecond() {
		return NEO_RPM_TO_FLYWHEEL_MPS.calculateReverseRatio(m_rightEncoder.getVelocity());
	}

	@Config(defaultValueNumeric = FlywheelConstants.P, name = "Set Flywheel P", tabName = "Flywheel Subsystem", width = 1, height = 1, columnIndex = 5, rowIndex = 0)
	public void setP(double newP) {
		m_pidLeftController.setP(newP);
		m_pidRightController.setP(newP);
	}

	@Config(defaultValueNumeric = FlywheelConstants.I, name = "Set Flywheel I", tabName = "Flywheel Subsystem", width = 1, height = 1, columnIndex = 5, rowIndex = 1)
	public void setI(double newI) {
		m_pidLeftController.setI(newI);
		m_pidRightController.setI(newI);
	}

	@Config(defaultValueNumeric = FlywheelConstants.D, name = "Set Flywheel D", tabName = "Flywheel Subsystem", width = 1, height = 1, columnIndex = 5, rowIndex = 2)
	public void setD(double newD) {
		m_pidLeftController.setD(newD);
		m_pidRightController.setD(newD);
	}

	@Config(defaultValueNumeric = FlywheelConstants.FF, name = "Set Flywheel FF", tabName = "Flywheel Subsystem", width = 1, height = 1, columnIndex = 5, rowIndex = 3)
	public void setFF(double newFF) {
		m_pidLeftController.setFF(newFF);
		m_pidRightController.setFF(newFF);
	}

	public double getCurrentDraw() {
		return m_flywheelLeftMotor.getOutputCurrent() + m_flywheelRightMotor.getOutputCurrent();
	}

	public void periodic() {
		brownoutStage = RobotState.brownoutStage;
	}
}
