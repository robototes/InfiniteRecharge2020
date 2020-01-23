package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.LimelightSubsystem;

public class LimelightReadCommand extends CommandBase {

	private LimelightSubsystem m_LimelightSubsystem;

	public LimelightReadCommand(LimelightSubsystem limelightSubsystem) {
		addRequirements(limelightSubsystem);
		m_LimelightSubsystem = limelightSubsystem;
	}

	@Override
	public void execute() {
		m_LimelightSubsystem.getValues();
	}

	public void end() {
		m_LimelightSubsystem.stopLimelight();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
