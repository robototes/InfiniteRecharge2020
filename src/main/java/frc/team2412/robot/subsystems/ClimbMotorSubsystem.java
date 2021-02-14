package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.ClimbConstants.CLIMB_OFFSET_HEIGHT;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.DEADBAND;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MAX_ARM_EXTENSION;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MAX_SPEED;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MIN_ARM_EXTENSION;
import static frc.team2412.robot.subsystems.constants.ClimbConstants.MOTOR_REVOLUTIONS_TO_INCHES;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.units.Distance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbHeight;

public class ClimbMotorSubsystem extends SubsystemBase {

	public Distance currentClimbHeight;

	private CANSparkMax leftMotor;
	private CANSparkMax rightMotor;

	private CANEncoder rightEncoder;
	private CANEncoder leftEncoder;

	private CANPIDController PIDController;

	private ClimbHeight reference;

	public ClimbMotorSubsystem(CANSparkMax leftMotor, CANSparkMax rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;

		this.rightMotor.setInverted(true);

		PIDController = this.rightMotor.getPIDController();

		rightEncoder = this.rightMotor.getEncoder();
		rightEncoder.setInverted(true);

		leftEncoder = this.leftMotor.getEncoder();
		PIDController.setP(0.005);
	}

	public boolean atReference() {
		return Math.abs(MOTOR_REVOLUTIONS_TO_INCHES
				.calculateReverseRatio(currentClimbHeight.subtract(reference.value))) > DEADBAND;
	}

	public void climbExtendArm() {
		setMotors(MAX_SPEED);
	}

	public void climbRetractArm() {
		setMotors(-MAX_SPEED);
	}

	public void climbStop() {
		setMotors(0);
	}

	public double getEncoderValue() {
		return rightEncoder.getPosition();
	}

	@Override
	public void periodic() {
		double heightFromOffset = MOTOR_REVOLUTIONS_TO_INCHES.calculateRatio(getEncoderValue());
		currentClimbHeight = new Distance(heightFromOffset).add(CLIMB_OFFSET_HEIGHT);
	}

	public void setMotors(double value) {
		// left : 0-75
		// right: -75 - 0
		setMotor(value, rightMotor, rightEncoder);
		setMotor(value, leftMotor, leftEncoder);

	}

	private void setMotor(double value, CANSparkMax motor, CANEncoder encoder) {
		if (value < 0 && MIN_ARM_EXTENSION < encoder.getPosition()
				|| 0 < value && encoder.getPosition() < MAX_ARM_EXTENSION) {
			motor.set(value);
		} else {
			motor.set(0);
		}
	}

	public void setReference(ClimbHeight newHeight) {
		reference = newHeight;
		Distance travelFromOffset = newHeight.value.subtract(CLIMB_OFFSET_HEIGHT);
		double wantedRotations = MOTOR_REVOLUTIONS_TO_INCHES.calculateReverseRatio(travelFromOffset);

		PIDController.setReference(wantedRotations, ControlType.kPosition);
	}

	public double getCurrentDraw() {
		return leftMotor.getOutputCurrent() + rightMotor.getOutputCurrent();
	}

	public boolean getIsClimbing() {
		return leftMotor.get() != 0 || rightMotor.get() != 0;
	}

}
