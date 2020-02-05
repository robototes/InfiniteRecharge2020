package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

/*
@Author Rahul Singh
*/

public class FlywheelSubsystem extends SubsystemBase implements Loggable {

	@Log.SpeedController
	private SpeedControllerGroup m_flywheelMotors;

	public FlywheelSubsystem(CANSparkMax flywheelMotor1, CANSparkMax flywheelMotor2) {
		flywheelMotor2.setInverted(true);
		this.m_flywheelMotors = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

	}

	public void shoot() {
		m_flywheelMotors.set(1.0);
	}

	public void stop() {
		m_flywheelMotors.set(0.0);
	}
}
