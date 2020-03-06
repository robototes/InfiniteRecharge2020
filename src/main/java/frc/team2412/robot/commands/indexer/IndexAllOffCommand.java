package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexAllOffCommand extends CommandBase {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;

	public IndexAllOffCommand(IndexerMotorSubsystem motorSubsystem, IndexerLiftMotorSubsystem liftMotorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(0);
		m_indexerMotorSubsystem.setBackMotor(0);
		m_indexerLiftMotorSubsystem.setMotorSpeed(0);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
