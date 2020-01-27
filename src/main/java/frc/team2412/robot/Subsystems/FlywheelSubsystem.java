package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
@Author Rahul Singh
*/

public class FlywheelSubsystem extends SubsystemBase {

	private SpeedControllerGroup m_droids;

	public FlywheelSubsystem(SpeedControllerGroup droids) {
		this.m_droids = droids;
	}

	public void Shoot() {
		m_droids.set(1.0);
	}

	public void Stop() {
		m_droids.set(0.0);
	}
}
