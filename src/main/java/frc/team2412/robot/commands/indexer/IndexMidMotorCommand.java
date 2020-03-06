package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexMidMotorCommand extends CommandBase {

	private IndexerMidMotorSubsystem m_indexerMotorSubsystem;

	public IndexMidMotorCommand(IndexerMidMotorSubsystem motorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
	}

	public void execute() {
		m_indexerMotorSubsystem.setUp();
	}

	public void end() {
		m_indexerMotorSubsystem.setOff();
	}
}
