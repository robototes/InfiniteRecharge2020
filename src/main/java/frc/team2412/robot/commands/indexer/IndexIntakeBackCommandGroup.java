package frc.team2412.robot.commands.indexer;

import static frc.team2412.robot.subsystems.constants.IndexerConstants.numBalls;
import static java.util.Map.entry;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeBackCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneBackCommand indexIntakeOneBackCommand;
	private IndexIntakeTwoThreeBackCommand indexIntakeTwoThreeBackCommand;
	private IndexIntakeFourFiveBackCommand indexIntakeFourFiveBackCommand;
	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand,
			indexIntakeTwoThreeBackCommand);
	private SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand,
			indexIntakeFourFiveBackCommand);

	public IndexIntakeBackCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;

		indexIntakeOneBackCommand = new IndexIntakeOneBackCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeTwoThreeBackCommand = new IndexIntakeTwoThreeBackCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		indexIntakeFourFiveBackCommand = new IndexIntakeFourFiveBackCommand(m_indexerSensorSubsystem);

		indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		Command command = new SelectCommand(Map.ofEntries(entry(0, indexIntakeOneBackCommand),
				entry(1, indexIntakeTwoThreeBackCommand), entry(2, indexSequenceTwo),
				entry(3, indexIntakeFourFiveBackCommand), entry(4, indexSequenceFour)), this::numBalls);
		addCommands(command);
	}

	public int numBalls() {
		return numBalls;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allInnerSensorsOn();
	}

}