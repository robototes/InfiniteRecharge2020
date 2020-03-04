package frc.team2412.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.HoodSubsystem;

public class HoodAdjustCommand extends CommandBase {
	private double m_increment;
	private HoodSubsystem m_HoodSubsystem;

	public HoodAdjustCommand(HoodSubsystem hoodSubsystem, double increment) {
		this.m_HoodSubsystem = hoodSubsystem;
		this.m_increment = increment;
	}

	@Override
	public void execute() {
		this.m_HoodSubsystem.add(this.m_increment);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
