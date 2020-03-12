package frc.team2412.robot.subsystems.intake;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.math.MathUtils;

import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.Loggable;

public interface IIntakeMotorSubsystem extends Loggable {

	public enum IntakeMotorSubsystemType {
		BACK(1), FRONT(1);

		public double SPEED;

		private IntakeMotorSubsystemType(double max_speed) {
			this.SPEED = max_speed;
		}
	}

	public CANSparkMax getMainMotor();

	public default double getCurrentDraw(){
		return getMainMotor().getOutputCurrent();
	}

	public IntakeMotorSubsystemType getType();

	public default void in() {
		this.set((RobotState.babyMode ? 0.5*getType().SPEED : getType().SPEED));
	}

	public default void out() {
		this.set(-(RobotState.babyMode ? 0.5*getType().SPEED : getType().SPEED));
	}
	
	public default void set(double speed) {
		double newSpeed = MathUtils.constrain(speed, -(RobotState.babyMode ? 0.5*getType().SPEED : getType().SPEED), (RobotState.babyMode ? 0.5*getType().SPEED : getType().SPEED));
		getMainMotor().set(newSpeed);
	}

	public default void stop() {
		this.set(0);
	}
}
