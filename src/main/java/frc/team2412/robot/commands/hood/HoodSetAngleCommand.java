package frc.team2412.robot.commands.hood;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;

public class HoodSetAngleCommand extends CommandBase {

	Supplier<ShooterDistanceDataPoint> m_distanceSupplier;
	HoodSubsystem m_HoodSubsystem;

	public HoodSetAngleCommand(HoodSubsystem m_HoodSubsystem, Supplier<ShooterDistanceDataPoint> m_distanceSupplier) {
		this.m_distanceSupplier = m_distanceSupplier;
		this.m_HoodSubsystem = m_HoodSubsystem;

		addRequirements(m_HoodSubsystem);
	}

	@Override
	public void execute() {
		m_HoodSubsystem.setServo(m_distanceSupplier.get().m_hoodAngle.value());
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
