package frc.team2412.robot.Commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.LimelightSubsystem;

public class LimelightAccurateReadCommand extends CommandBase {

	private LimelightSubsystem m_LimelightSubsystem;

	public LimelightAccurateReadCommand(LimelightSubsystem limelightSubsystem) {
		addRequirements(limelightSubsystem);
		m_LimelightSubsystem = limelightSubsystem;
	}

	@Override
	public void end(boolean cancelled) {
		if (!cancelled)
			m_LimelightSubsystem.stopLimelight();
	}

	@Override
	public void execute() {
		m_LimelightSubsystem.getValues();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
