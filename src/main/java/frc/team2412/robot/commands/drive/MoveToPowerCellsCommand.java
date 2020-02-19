package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class MoveToPowerCellsCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	

	public MoveToPowerCellsCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		m_driveBaseSubsystem = driveBaseSubsystem;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.getMoveToPowerCellCommand();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
