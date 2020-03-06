package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem indexerSensorSubsystem, IndexerLiftMotorSubsystem indexerLiftMotorSubsystem, IndexerMotorSubsystem indexMotorSubsystem,
			IntakeOnOffSubsystem intakeSubsystem) {
		m_indexerMotorSubsystem = indexMotorSubsystem;
		m_intakeOnOffSubsystem = intakeSubsystem;
		m_indexerLiftMotorSubsystem = indexerLiftMotorSubsystem;

		addCommands(new IndexFrontShootCommand(indexMotorSubsystem, indexerLiftMotorSubsystem, intakeSubsystem),
				new ConditionalCommand(new WaitCommand(3), new WaitCommand(0),
						indexerSensorSubsystem::allFrontSensorsOff),
				new IndexBackShootCommand(indexMotorSubsystem, indexerLiftMotorSubsystem, intakeSubsystem), new WaitCommand(2));
	}

	@Override
	public void initialize() {
		super.initialize();
		m_indexerMotorSubsystem.setLifting(true);
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.setLifting(false);
		m_indexerMotorSubsystem.stopAllMotors();
		m_intakeOnOffSubsystem.setIntake(0);
	}
}
