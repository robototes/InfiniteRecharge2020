package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import jdk.tools.jlink.internal.DirArchive;

public class MoveToPowerCellsCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	

	public MoveToPowerCellsCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		m_driveBaseSubsystem = driveBaseSubsystem;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.drive();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
