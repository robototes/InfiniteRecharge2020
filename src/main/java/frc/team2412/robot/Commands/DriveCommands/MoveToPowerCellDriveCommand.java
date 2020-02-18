package frc.team2412.robot.Commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

public class MoveToPowerCellDriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;

	public MoveToPowerCellDriveCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
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
