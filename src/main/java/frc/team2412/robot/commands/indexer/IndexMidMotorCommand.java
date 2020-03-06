package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexMidMotorCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexMidMotorCommand(IndexerMotorSubsystem motorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
	}

public void execute(){
	m_indexerMotorSubsystem.setMidMotor(0.7);
}

public void end(){
	m_indexerMotorSubsystem.setMidMotor(0);
}
}
