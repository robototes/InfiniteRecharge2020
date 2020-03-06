package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexAllOffCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerMidMotorSubsystem m_indexerMidMotorSubsystem;

	public IndexAllOffCommand(IndexerMotorSubsystem motorSubsystem, IndexerMidMotorSubsystem midMotorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_indexerMidMotorSubsystem = midMotorSubsystem;
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(0);
		m_indexerMotorSubsystem.setBackMotor(0);
		m_indexerMidMotorSubsystem.setOff();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
