package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.FlywheelConstants;

public class FlywheelSubsystem extends SubsystemBase {

	private CANSparkMax leftMotor;
	private CANEncoder leftEncoder;
	private CANPIDController leftPIDController;

	private CANSparkMax rightMotor;
	private CANEncoder rightEncoder;
	private CANPIDController rightPIDController;

	public FlywheelSubsystem(CANSparkMax leftMotor, CANSparkMax rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;

		this.rightMotor.setInverted(true);

		configureMotor(this.leftMotor, leftEncoder, leftPIDController);
		configureMotor(this.rightMotor, rightEncoder, rightPIDController);
		setPIDtoDefault();
	}

	private void configureMotor(CANSparkMax motor, CANEncoder encoder, CANPIDController pidController) {
		encoder = motor.getEncoder();
		pidController = rightMotor.getPIDController();
		motor.setIdleMode(IdleMode.kCoast);
	}

	public void setSpeed(double speed) {
		if (speed <= 0) {
			leftMotor.set(speed);
			rightMotor.set(speed);
		}
	}

	public void setPIDtoDefault() {
		setP(FlywheelConstants.P);
		setI(FlywheelConstants.I);
		setD(FlywheelConstants.D);
		setFF(FlywheelConstants.FF);
	}

	public void shoot() {
		// flywheelLeftMotor.set(1.0);
	}

	public void stop() {
		// flywheelLeftMotor.set(0.0);
	}

	public double getSpeed() {
		return leftMotor.get();
	}

	public void setRPM(double wantedVelocity) {
		// double wantedRPM = -NEO_RPM_TO_FLYWHEEL_MPS.calculateRatio(wantedVelocity);

		leftPIDController.setReference(-wantedVelocity, ControlType.kVelocity);
		rightPIDController.setReference(-wantedVelocity, ControlType.kVelocity);
	}

	public double currentLeftSpeed() {
		return leftEncoder.getVelocity();
	}

	public double currentRightSpeed() {
		return -rightEncoder.getVelocity();
	}

	public void setP(double newP) {
		leftPIDController.setP(newP);
		rightPIDController.setP(newP);
	}

	public void setI(double newI) {
		leftPIDController.setI(newI);
		rightPIDController.setI(newI);
	}

	public void setD(double newD) {
		leftPIDController.setD(newD);
		rightPIDController.setD(newD);
	}

	public void setFF(double newFF) {
		leftPIDController.setFF(newFF);
		rightPIDController.setFF(newFF);
	}

	public double getCurrentDraw() {
		return leftMotor.getOutputCurrent() + rightMotor.getOutputCurrent();
	}

}
