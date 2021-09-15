package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.FlywheelConstants;

public class FlywheelSubsystem extends SubsystemBase {

	private final CANSparkMax leftMotor;
	private final CANEncoder leftEncoder;
	private final CANPIDController leftPIDController;

	private final CANSparkMax rightMotor;
	private final CANEncoder rightEncoder;
	private final CANPIDController rightPIDController;

	public FlywheelSubsystem(CANSparkMax leftMotor, CANSparkMax rightMotor) {
		this.leftMotor = leftMotor;
		this.leftEncoder = leftMotor.getEncoder();
		this.leftPIDController = leftMotor.getPIDController();
		this.leftMotor.setIdleMode(IdleMode.kCoast);

		this.rightMotor = rightMotor;
		this.rightMotor.setInverted(true);
		this.rightEncoder = rightMotor.getEncoder();
		this.rightPIDController = rightMotor.getPIDController();
		this.rightMotor.setIdleMode(IdleMode.kCoast);

		setPIDtoDefault();
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
