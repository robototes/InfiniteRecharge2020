package frc.team2412.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ClimbMotorSubsystem;
import frc.team2412.robot.subsystems.constants.ClimbConstants.ClimbHeight;

public class ClimbToBottomHeightCommand extends CommandBase {

	ClimbMotorSubsystem m_ClimbMotorSubsystem;

	public ClimbToBottomHeightCommand(ClimbMotorSubsystem climbMotorSubsystem) {
		m_ClimbMotorSubsystem = climbMotorSubsystem;
		addRequirements(climbMotorSubsystem);
	}

	@Override
	public void execute() {
		m_ClimbMotorSubsystem.setReference(ClimbHeight.BOTTOM);
	}

	@Override
	public boolean isFinished() {
		return m_ClimbMotorSubsystem.atReference();
	}
}
