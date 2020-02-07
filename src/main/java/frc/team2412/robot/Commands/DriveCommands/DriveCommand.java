package frc.team2412.robot.Commands.DriveCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

public class DriveCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;
	private Joystick m_joystick;
	private Joystick m_joystick2;
	private Button m_button;

	public DriveCommand(DriveBaseSubsystem driveBaseSubsystem, Joystick joystick, Joystick joystick2, Button button) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
		this.m_joystick = joystick;
		m_joystick2 = joystick2;
		m_button = button;
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
