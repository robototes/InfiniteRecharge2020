package frc.team2412.robot.commands.indexer;

import static java.util.Map.Entry;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeBackCommandGroup extends ParallelCommandGroup {

	public IndexIntakeBackCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		IndexerSensorSubsystem m_indexerSensorSubsystem = sensorSubsystem;
		IndexerMotorSubsystem m_indexerMotorSubsystem = motorSubsystem;

		IndexIntakeOneBackCommand indexIntakeOneBackCommand = new IndexIntakeOneBackCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexIntakeTwoBackCommand indexIntakeTwoBackCommand = new IndexIntakeTwoBackCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexIntakeThreeBackCommand indexIntakeThreeBackCommand = new IndexIntakeThreeBackCommand(
				m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		IndexIntakeFourBackCommand indexIntakeFourBackCommand = new IndexIntakeFourBackCommand(
				m_indexerSensorSubsystem);
		IndexIntakeFiveBackCommand indexIntakeFiveBackCommand = new IndexIntakeFiveBackCommand(
				m_indexerSensorSubsystem);

		IndexSwitchTwoCommand indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexSwitchFourCommand indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);

		SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand,
				indexIntakeThreeBackCommand);
		SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand,
				indexIntakeFiveBackCommand);

		Command command = new SelectCommand(
				Map.ofEntries(entry(0, indexIntakeOneBackCommand), entry(1, indexIntakeTwoBackCommand),
						entry(2, indexSequenceTwo), entry(3, indexIntakeFourBackCommand), entry(4, indexSequenceFour)),
				this::numBalls);
		addCommands(command);
	}

	public int numBalls() {
		return RobotState.m_ballCount;
	}
}