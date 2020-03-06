package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexAllOut extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerMidMotorSubsystem m_indexerMidMotorSubsystem;

	public IndexAllOut(IndexerMotorSubsystem motorSubsystem, IndexerMidMotorSubsystem midMotorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(1);
		m_indexerMotorSubsystem.setBackMotor(1);
		m_indexerMidMotorSubsystem.setDown();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
