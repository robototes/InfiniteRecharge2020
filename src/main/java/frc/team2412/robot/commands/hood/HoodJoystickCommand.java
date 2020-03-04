package frc.team2412.robot.commands.hood;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.HoodSubsystem;

public class HoodJoystickCommand extends CommandBase {

	private HoodSubsystem m_hoodSubsystem;
	private DoubleSupplier m_angleSupplier;

	public HoodJoystickCommand(HoodSubsystem hoodSubsystem, DoubleSupplier angleSupplier) {
		this.m_angleSupplier = angleSupplier;
		this.m_hoodSubsystem = hoodSubsystem;
	}

	@Override
	public void execute() {
		m_hoodSubsystem.setServo(m_angleSupplier.getAsDouble());
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
