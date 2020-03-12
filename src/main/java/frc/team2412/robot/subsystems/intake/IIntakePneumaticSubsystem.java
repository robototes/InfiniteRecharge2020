package frc.team2412.robot.subsystems.intake;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.Loggable;

public interface IIntakePneumaticSubsystem extends Loggable {

	public enum IntakePneumaticSubsystemType {
		BACK, FRONT;
	}

	public Solenoid getMainSolenoid();

	public IntakePneumaticSubsystemType getType();

	public default void in() {
		getMainSolenoid().set(false);
	}

	public default void set(boolean val){
		getMainSolenoid().set(val);
	}

	public default void out() {
		getMainSolenoid().set(true);
	}
}
