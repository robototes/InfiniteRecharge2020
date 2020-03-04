package frc.team2412.robot.commands.indexerOld;

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

	public IndexIntakeFrontCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		IndexerSensorSubsystem m_indexerSensorSubsystem = sensorSubsystem;
		IndexerMotorSubsystem m_indexerMotorSubsystem = motorSubsystem;

		IndexIntakeOneFrontCommand indexIntakeOneFrontCommand = new IndexIntakeOneFrontCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexIntakeTwoFrontCommand indexIntakeTwoFrontCommand = new IndexIntakeTwoFrontCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexIntakeThreeFrontCommand indexIntakeThreeFrontCommand = new IndexIntakeThreeFrontCommand(
				m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		IndexIntakeFourFrontCommand indexIntakeFourFrontCommand = new IndexIntakeFourFrontCommand(
				m_indexerSensorSubsystem);
		IndexIntakeFiveFrontCommand indexIntakeFiveFrontCommand = new IndexIntakeFiveFrontCommand(
				m_indexerSensorSubsystem);

		IndexSwitchTwoCommand indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		IndexSwitchFourCommand indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);

		SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand,
				indexIntakeThreeFrontCommand);
		SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand,
				indexIntakeFiveFrontCommand);

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