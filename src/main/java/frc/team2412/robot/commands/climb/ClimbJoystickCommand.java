package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;

public class ClimbJoystickCommand extends CommandBase {

	private ClimbMotorSubsystem m_ClimbMotorSubsystem;
	private Joystick m_joystick;

	public ClimbJoystickCommand(Joystick m_joystick, ClimbMotorSubsystem m_ClimbMotorSubsystem) {
		this.m_joystick = m_joystick;
		this.m_ClimbMotorSubsystem = m_ClimbMotorSubsystem;
	}

	@Override
	public void execute() {
		double joystickValue = m_joystick.getY();

		m_ClimbMotorSubsystem.setMotors(joystickValue);
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
