package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.intake.IntakeFrontMotorSubsystem;

public class IntakeFrontOffCommand extends CommandBase {

	private IntakeFrontMotorSubsystem m_intakeSubsystem;

	public IntakeFrontOffCommand(IntakeFrontMotorSubsystem intakeSubsystem) {
		m_intakeSubsystem = intakeSubsystem;
		addRequirements(intakeSubsystem);
		
	}

	@Override
	public void execute() {
		m_intakeSubsystem.stop();
	}
	@Override
	public boolean isFinished() {
		return true;
	}

}
