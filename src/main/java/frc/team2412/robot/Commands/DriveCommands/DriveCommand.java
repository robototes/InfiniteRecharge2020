package frc.team2412.robot.Commands.DriveCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

public class DriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	private Joystick m_joystick;

	public DriveCommand(DriveBaseSubsystem driveBaseSubsystem, Joystick joystick) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
		this.m_joystick = joystick;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.drive(m_joystick);
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
