package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexAllOnCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerMidMotorSubsystem m_indexerMidMotorSubsystem;

	public IndexAllOnCommand(IndexerMotorSubsystem motorSubsystem, IndexerMidMotorSubsystem midMotorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_indexerMidMotorSubsystem = midMotorSubsystem;
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(1);
		m_indexerMotorSubsystem.setBackMotor(1);
		m_indexerMidMotorSubsystem.setUp();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
