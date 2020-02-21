package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class MoveFromPowerCellsCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	

	public MoveFromPowerCellsCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		m_driveBaseSubsystem = driveBaseSubsystem;
	}

	@Override
	public void execute() {
		CommandScheduler.getInstance().schedule(m_driveBaseSubsystem.getMoveFromPowerCellPathFromPathWeaverCommand());
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
