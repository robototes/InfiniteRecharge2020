package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.IntakeConstants.MAX_INTAKE_SPEED;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeDirection;

public class IntakeMotorSubsystem extends SubsystemBase {

	public static IntakeDirection intakeDirection;

	public CANSparkMax frontMotor;
	public CANSparkMax backMotor;

	public IntakeMotorSubsystem(CANSparkMax frontMotor, CANSparkMax backMotor) {
		this.frontMotor = frontMotor;
		this.backMotor = backMotor;

		this.backMotor.setInverted(true);
		this.frontMotor.setInverted(true);
	}

	public void set(CANSparkMax motor, double speed) {
		motor.set(speed);
	}

	public void backIntakeOff() {
		set(backMotor, 0);
	}

	public void backIntakeIn() {
		set(backMotor, MAX_INTAKE_SPEED);
	}

	public void backIntakeOut() {
		set(backMotor, -MAX_INTAKE_SPEED);
	}

	public void frontIntakeOff() {
		set(frontMotor, 0);
	}

	public void frontIntakeIn() {
		set(frontMotor, MAX_INTAKE_SPEED);
	}

	public void frontIntakeOut() {
		set(frontMotor, -MAX_INTAKE_SPEED);
	}

	public void intakeOff() {
		backIntakeOff();
		frontIntakeOff();
	}

	public void intakeIn() {
		backIntakeIn();
		frontIntakeIn();
	}

	public void setIntake(double speed) {
		set(frontMotor, speed);
		set(backMotor, speed);
	}

	public double getCurrentDraw() {
		return backMotor.getOutputCurrent() + frontMotor.getOutputCurrent();
	}

	public IntakeDirection getIntakeDirection() {
		return intakeDirection;
	}

	public void periodic() {
		if (frontMotor.get() != 0 && backMotor.get() != 0) {
			intakeDirection = IntakeDirection.BOTH;
		} else if (frontMotor.get() != 0) {
			intakeDirection = IntakeDirection.FRONT;
		} else if (backMotor.get() != 0) {
			intakeDirection = IntakeDirection.BACK;
		} else {
			intakeDirection = IntakeDirection.NONE;
		}
	}
}
