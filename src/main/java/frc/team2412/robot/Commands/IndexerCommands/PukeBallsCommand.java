package frc.team2412.robot.Commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class PukeBallsCommand extends CommandBase {

	IndexerSubsystem m_subsystem;
	IntakeOnOffSubsystem m_intakeSubsystem;

	public PukeBallsCommand(IndexerSubsystem subsystem, IntakeOnOffSubsystem intakeSubsystem) {
		m_subsystem = subsystem;
		m_intakeSubsystem = intakeSubsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		m_intakeSubsystem.setIntake(-1);
		m_subsystem.puke();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
