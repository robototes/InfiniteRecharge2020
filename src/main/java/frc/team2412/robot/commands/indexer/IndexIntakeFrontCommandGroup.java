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
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeFrontCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneFrontCommand indexIntakeOneFrontCommand;
	private IndexIntakeTwoThreeFrontCommand indexIntakeTwoThreeFrontCommand;
	private IndexIntakeFourFiveFrontCommand indexIntakeFourFiveFrontCommand;
	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexIntakeTwoThreeFrontCommand,
			indexSwitchTwoCommand);
	private SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexIntakeFourFiveFrontCommand,
			indexSwitchFourCommand);

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

//		ConditionalCommand command = new ConditionalCommand();
		Command command = new SelectCommand(Map.ofEntries(entry(0, indexIntakeOneFrontCommand),
				entry(1, indexSequenceTwo), entry(2, indexIntakeTwoThreeFrontCommand), entry(3, indexSequenceFour),
				entry(4, indexIntakeFourFiveFrontCommand)), this::numBalls);
		addCommands(command);
		// addCommands(indexIntakeOneFrontCommand, indexIntakeTwoThreeFrontCommand,
		// indexIntakeFourFiveBackCommand, indexSwitchTwoCommand,
		// indexSwitchFourCommand);
	}

	public int numBalls() {
		return numBalls;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allInnerSensorsOn();
	}

}
