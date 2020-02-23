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

public class IndexIntakeFrontCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneFrontCommand indexIntakeOneFrontCommand;

	private IndexIntakeTwoFrontCommand indexIntakeTwoFrontCommand;
	private IndexIntakeThreeFrontCommand indexIntakeThreeFrontCommand;

	private IndexIntakeFourFrontCommand indexIntakeFourFrontCommand;
	private IndexIntakeFiveFrontCommand indexIntakeFiveFrontCommand;

	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo, indexSequenceFour;

	public IndexIntakeFrontCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;

		indexIntakeOneFrontCommand = new IndexIntakeOneFrontCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeTwoFrontCommand = new IndexIntakeTwoFrontCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeThreeFrontCommand = new IndexIntakeThreeFrontCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		indexIntakeFourFrontCommand = new IndexIntakeFourFrontCommand(m_indexerSensorSubsystem);
		indexIntakeFiveFrontCommand = new IndexIntakeFiveFrontCommand(m_indexerSensorSubsystem);

		indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);

		indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand, indexIntakeThreeFrontCommand);
		indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand, indexIntakeFiveFrontCommand);

		Command command = new SelectCommand(
				Map.ofEntries(entry(0, indexIntakeOneFrontCommand), entry(1, indexIntakeTwoFrontCommand),
						entry(2, indexSequenceTwo), entry(3, indexIntakeFourFrontCommand), entry(4, indexSequenceFour)),
				this::numBalls);
		addCommands(command);
	}

	public int numBalls() {
		return RobotState.m_ballCount;
	}

}