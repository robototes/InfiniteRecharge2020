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
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IndexIntakeFrontCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneFrontCommand indexIntakeOneFrontCommand;
	private IndexIntakeTwoThreeFrontCommand indexIntakeTwoThreeFrontCommand;
	private IndexIntakeFourFiveFrontCommand indexIntakeFourFiveFrontCommand;
	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexSwitchTwoCommand,
			indexIntakeTwoThreeFrontCommand);
	private SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexSwitchFourCommand,
			indexIntakeFourFiveFrontCommand);

	public IndexIntakeFrontCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem,
			IntakeOnOffSubsystem intakeMotor, IntakeUpDownSubsystem intakeUpDown) {

		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;

		indexIntakeOneFrontCommand = new IndexIntakeOneFrontCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeTwoThreeFrontCommand = new IndexIntakeTwoThreeFrontCommand(m_indexerSensorSubsystem,
				m_indexerMotorSubsystem);
		indexIntakeFourFiveFrontCommand = new IndexIntakeFourFiveFrontCommand(m_indexerSensorSubsystem, intakeUpDown,
				intakeMotor);

		indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);

		Command command = new SelectCommand(Map.ofEntries(entry(0, indexIntakeOneFrontCommand),
				entry(1, indexIntakeTwoThreeFrontCommand), entry(2, indexSequenceTwo),
				entry(3, indexIntakeFourFiveFrontCommand), entry(4, indexSequenceFour)), this::numBalls);
		addCommands(command);
	}

	public int numBalls() {
		return RobotState.getBallCount();
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allInnerSensorsOn();
	}

}