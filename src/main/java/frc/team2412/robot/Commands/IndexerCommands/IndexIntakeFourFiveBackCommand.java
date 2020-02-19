package frc.team2412.robot.Commands.IndexerCommands;

import static frc.team2412.robot.Subsystems.constants.IndexerConstants.numBalls;
import static frc.team2412.robot.Subsystems.constants.IndexerConstants.unbalancedSide;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.Subsystems.constants.IndexerConstants;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeFourFiveBackCommand extends CommandBase {
	private IndexerSensorSubsystem m_indexerSensorSubsystem;

	public IndexIntakeFourFiveBackCommand(IndexerSensorSubsystem sensorSubsystem) {
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
			unbalancedSide = IndexerConstants.UnbalancedSide.BACK;
			numBalls++;
			return true;
		} else {
			return false;
		}
	}

}
