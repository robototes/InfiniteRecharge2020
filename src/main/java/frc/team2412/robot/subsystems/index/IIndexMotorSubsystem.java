package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.math.MathUtils;

import frc.team2412.robot.subsystems.constants.IndexerConstants;

public interface IIndexMotorSubsystem {

	public enum IndexMotorSubsystemType {
		BACK(IndexerConstants.MAX_SPEED), FRONT(IndexerConstants.MAX_SPEED), LIFT(IndexerConstants.MAX_LIFT_SPEED);

		public double SPEED;

		private IndexMotorSubsystemType(double max_speed) {
			this.SPEED = max_speed;
		}
	}

	public default void addPID(double addRotations) {
		this.pid(addRotations + getEncoder().getPosition());
	}

	public default void configPID(CANPIDController controller) {
		controller.setP(IndexerConstants.PID_P);
		controller.setI(IndexerConstants.PID_I);
		controller.setD(IndexerConstants.PID_D);

		controller.setOutputRange(-this.getType().SPEED, -this.getType().SPEED);
	}

	public double getCurrentDraw();

	public CANEncoder getEncoder();

	public CANSparkMax getMainMotor();

	public CANPIDController getPIDController();

	public IndexMotorSubsystemType getType();

	public default void in() {
		this.set(-this.getType().SPEED/*0.5*/);
	}

	public default void out() {
		this.set(this.getType().SPEED);
	}

	public default void pid(double rotations) {
		this.getPIDController().setReference(rotations, ControlType.kPosition);
	}

	public default void set(double speed) {
		double newSpeed = MathUtils.constrain(speed, -getType().SPEED, getType().SPEED);
		getMainMotor().set(newSpeed/*speed*/);
	}

	public default void stop() {
		this.set(0);
	}
}
