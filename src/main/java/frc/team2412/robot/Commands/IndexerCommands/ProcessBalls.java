package frc.team2412.robot.Commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.IndexerSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class ProcessBalls extends CommandBase {
	IndexerSubsystem m_Subsystem;

	public ProcessBalls(IndexerSubsystem subsystem) {
		m_Subsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		//get the button value for shooting
		boolean b = false;
		m_Subsystem.indexerLogic(b);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
