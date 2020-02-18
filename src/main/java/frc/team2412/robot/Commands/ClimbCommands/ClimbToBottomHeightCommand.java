package frc.team2412.robot.Commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.Subsystems.constants.ClimbConstants.ClimbHeight;

public class ClimbToBottomHeightCommand extends CommandBase {

	ClimbMotorSubsystem m_ClimbMotorSubsystem;

	public ClimbToBottomHeightCommand(ClimbMotorSubsystem climbMotorSubsystem) {

		m_ClimbMotorSubsystem = climbMotorSubsystem;
		addRequirements(climbMotorSubsystem);
	}

	@Override
	public void execute() {
		m_ClimbMotorSubsystem.climbToHeight(ClimbHeight.BOTTOM);
	}

	@Override
	public boolean isFinished() {
		return (m_ClimbMotorSubsystem.m_currentClimbHeight >= ClimbHeight.MIDDLE.value);
	}
}
