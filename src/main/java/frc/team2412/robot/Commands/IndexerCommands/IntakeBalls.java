package frc.team2412.robot.Commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
import frc.team2412.robot.Subsystems.constants.IndexerConstants.IndexerDirection;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IntakeBalls extends CommandBase {

	IndexerSubsystem m_Subsystem;

	public IntakeBalls(IndexerSubsystem subsystem) {
		m_Subsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		// run the example method
		IndexerDirection dir;
		if (m_Subsystem.m_intakeFront.get()) {
			dir = IndexerDirection.FRONT;
		} else if (m_Subsystem.m_intakeBack.get()) {
			dir = IndexerDirection.BACK;
		} else {
			dir = IndexerDirection.NONE;
		}
		m_Subsystem.intake(m_Subsystem.getNumBalls() + 1, dir);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
