package frc.team2412.robot.Commands.ControlPanelCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ControlPanelColorSubsystem;

public class RotateControlPanelCommand extends CommandBase {

	private ControlPanelColorSubsystem m_controlPanelColorSubsystem;

	public RotateControlPanelCommand(ControlPanelColorSubsystem controlPanelColorSubsystem) {
		addRequirements(controlPanelColorSubsystem);
		this.m_controlPanelColorSubsystem = controlPanelColorSubsystem;
	}

	@Override
	public void execute() {
		// run the example method
		m_controlPanelColorSubsystem.rotateControlPanel();

	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
