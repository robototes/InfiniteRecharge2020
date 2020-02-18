package frc.team2412.robot.Commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

public class MoveFromPowerCellDriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;

	public MoveFromPowerCellDriveCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.getMoveFromPowerCellCommand();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
