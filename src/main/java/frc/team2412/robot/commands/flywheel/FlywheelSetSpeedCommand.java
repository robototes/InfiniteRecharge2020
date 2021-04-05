package frc.team2412.robot.commands.flywheel;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;

public class FlywheelSetSpeedCommand extends CommandBase {

	FlywheelSubsystem m_flywheelSubsystem;
	Supplier<ShooterDistanceDataPoint> m_distanceSupplier = null;
	double m_RPM = 0.0;

	public FlywheelSetSpeedCommand(FlywheelSubsystem m_flywheelSubsystem,
			Supplier<ShooterDistanceDataPoint> m_distanceSupplier) {
		this.m_flywheelSubsystem = m_flywheelSubsystem;
		this.m_distanceSupplier = m_distanceSupplier;
	}

	public FlywheelSetSpeedCommand(FlywheelSubsystem flywheelSubsystem,
			double rpm) {
		this.m_flywheelSubsystem = flywheelSubsystem;
		this.m_RPM = rpm;
	}

	@Override
	public void execute() {
		if (m_distanceSupplier != null) {
			m_flywheelSubsystem.setRPM(m_distanceSupplier.get().m_shooterPower.value());
		} else {
			m_flywheelSubsystem.setRPM(m_RPM);
		}
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
