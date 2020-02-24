package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeFourBackCommand extends CommandBase {
	private IndexerSensorSubsystem m_indexerSensorSubsystem;

	public IndexIntakeFourBackCommand(IndexerSensorSubsystem sensorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		addRequirements(sensorSubsystem);
	}

	@Override
	public void execute() {
		// INTAKE CLOSES
	}

	@Override
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.getIndexBackSensorValue()) {
			RobotState.m_unbalancedSide = RobotState.UnbalancedSide.BACK;
			RobotState.m_ballCount++;
			return true;
		} else {
			return false;
		}
	}

}
