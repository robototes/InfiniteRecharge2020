package frc.team2412.robot.Commands.IndexerCommands;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.Subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.Subsystems.constants.IndexerConstants;

import static frc.team2412.robot.Subsystems.constants.IndexerConstants.numBalls;
import static java.util.Map.entry;
//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeBackCommandGroup extends ParallelCommandGroup {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	private IndexIntakeOneBackCommand indexIntakeOneBackCommand;
	private IndexIntakeTwoThreeBackCommand indexIntakeTwoThreeBackCommand;
	private IndexIntakeFourFiveBackCommand indexIntakeFourFiveBackCommand;
	private IndexSwitchTwoCommand indexSwitchTwoCommand;
	private IndexSwitchFourCommand indexSwitchFourCommand;

	private SequentialCommandGroup indexSequenceTwo = new SequentialCommandGroup(indexIntakeTwoThreeBackCommand, indexSwitchTwoCommand);
	private SequentialCommandGroup indexSequenceFour = new SequentialCommandGroup(indexIntakeFourFiveBackCommand, indexSwitchFourCommand);


	public IndexIntakeBackCommandGroup(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {

		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;


		indexIntakeOneBackCommand = new IndexIntakeOneBackCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeTwoThreeBackCommand = new IndexIntakeTwoThreeBackCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexIntakeFourFiveBackCommand = new IndexIntakeFourFiveBackCommand(m_indexerSensorSubsystem);

		indexSwitchTwoCommand = new IndexSwitchTwoCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
		indexSwitchFourCommand = new IndexSwitchFourCommand(m_indexerSensorSubsystem, m_indexerMotorSubsystem);
	//HI
//		ConditionalCommand command = new ConditionalCommand();
		Command command = new SelectCommand(
				Map.ofEntries(
						entry(0, indexIntakeOneBackCommand),
						entry(1, indexSequenceTwo),
						entry(2, indexIntakeTwoThreeBackCommand),
						entry(3, indexSequenceFour),
						entry(4, indexIntakeFourFiveBackCommand)
				),
				this::numBalls
		);
		addCommands(command);
		//addCommands(indexIntakeOneBackCommand, indexIntakeTwoThreeBackCommand, indexIntakeFourFiveBackCommand, indexSwitchTwoCommand, indexSwitchFourCommand);
	}
	public int numBalls(){
		return numBalls;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allInnerSensorsOn();
	}

}
