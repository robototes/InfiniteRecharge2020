package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class OneJoystickDriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	private Joystick m_joystick;

	public OneJoystickDriveCommand(DriveBaseSubsystem driveBaseSubsystem, Joystick joystick) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
		this.m_joystick = joystick;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.oneJoystickDrive(m_joystick);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
