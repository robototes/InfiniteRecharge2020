package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class DriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	private Joystick m_joystick;
	private Joystick m_joystick2;
	private Button m_button;

	public DriveCommand(DriveBaseSubsystem driveBaseSubsystem, Joystick joystick, Joystick joystick2, Button indexerShootButton) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
		this.m_joystick = joystick;
		m_joystick2 = joystick2;
		m_button = indexerShootButton;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.drive(m_joystick, m_joystick2, m_button);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
