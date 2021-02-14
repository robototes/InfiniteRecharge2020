package frc.team2412.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LimelightSubsystem;

public class LimelightReadCommand extends CommandBase {

	private LimelightSubsystem m_LimelightSubsystem;

	public LimelightReadCommand(LimelightSubsystem limelightSubsystem) {
		addRequirements(limelightSubsystem);
		m_LimelightSubsystem = limelightSubsystem;
	}

	public void intialize() {
		m_LimelightSubsystem.startLimelight();
	}

	@Override
	public void end(boolean cancelled) {
		if (cancelled)
			m_LimelightSubsystem.stopLimelight();
	}

	@Override
	public void execute() {
		m_LimelightSubsystem.getValues();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
