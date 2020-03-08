package frc.team2412.robot.commands.flywheel;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;

public class FlywheelSetSpeedCommand extends CommandBase {

	FlywheelSubsystem m_flywheelSubsystem;
	Supplier<ShooterDistanceDataPoint> m_distanceSupplier;

	public FlywheelSetSpeedCommand(FlywheelSubsystem m_flywheelSubsystem,
			Supplier<ShooterDistanceDataPoint> m_distanceSupplier) {
		this.m_flywheelSubsystem = m_flywheelSubsystem;
		this.m_distanceSupplier = m_distanceSupplier;
	}

	@Override
	public void execute() {
		m_flywheelSubsystem.setSpeed(m_distanceSupplier.get().m_shooterPower.value());
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
