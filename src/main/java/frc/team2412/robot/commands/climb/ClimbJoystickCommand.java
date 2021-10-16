package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;

public class ClimbJoystickCommand extends CommandBase {

	private ClimbMotorSubsystem m_ClimbMotorSubsystem;
	private Joystick m_joystick;
	final static double m_joystickDeadZone = 0.12;

	public ClimbJoystickCommand(Joystick m_joystick, ClimbMotorSubsystem m_ClimbMotorSubsystem) {
		this.m_joystick = m_joystick;
		this.m_ClimbMotorSubsystem = m_ClimbMotorSubsystem;
	}

	@Override
	public void execute() {
		double joystickValue = getCorrectedJoystickValue(m_joystick.getY());
		m_ClimbMotorSubsystem.setMotors(joystickValue);
	}
	
	static double getCorrectedJoystickValue(double uncorrectedJoystickValue) {
		if (Math.abs(uncorrectedJoystickValue) < m_joystickDeadZone) {
			return 0;
		}

		double scaleValue = 1.0 / (1.0 - m_joystickDeadZone);
		double offsetValue = uncorrectedJoystickValue > 0 ? m_joystickDeadZone : -m_joystickDeadZone; 
		return (uncorrectedJoystickValue - offsetValue) * scaleValue;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
