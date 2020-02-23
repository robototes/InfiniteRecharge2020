package frc.team2412.robot.commands.indexer;

import static java.util.Map.entry;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeBackCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneBackCommand indexIntakeOneBackCommand;

	private IndexIntakeTwoBackCommand indexIntakeTwoBackCommand;
	private IndexIntakeThreeBackCommand indexIntakeThreeBackCommand;

	private IndexIntakeFourBackCommand indexIntakeFourBackCommand;
	private IndexIntakeFiveBackCommand indexIntakeFiveBackCommand;

	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo, indexSequenceFour;

	public IndexIntakeBackCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;

		indexIntakeOneBackCommand = new IndexIntakeOneBackCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeTwoBackCommand = new IndexIntakeTwoBackCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeThreeBackCommand = new IndexIntakeThreeBackCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		indexIntakeFourBackCommand = new IndexIntakeFourBackCommand(m_indexerSensorSubsystem);
		indexIntakeFiveBackCommand = new IndexIntakeFiveBackCommand(m_indexerSensorSubsystem);

		indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);

		indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand, indexIntakeThreeBackCommand);
		indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand, indexIntakeFiveBackCommand);

		Command command = new SelectCommand(
				Map.ofEntries(entry(0, indexIntakeOneBackCommand), entry(1, indexIntakeTwoBackCommand),
						entry(2, indexSequenceTwo), entry(3, indexIntakeFourBackCommand), entry(4, indexSequenceFour)),
				this::numBalls);
		addCommands(command);
	}

	public int numBalls() {
		return RobotState.m_ballCount;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allInnerSensorsOn();
	}

}