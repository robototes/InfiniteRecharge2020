package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IntakeMotorSubsystem m_intakeOnOffSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexMotorSubsystem,
			IntakeMotorSubsystem intakeSubsystem) {
		m_indexerMotorSubsystem = indexMotorSubsystem;
		m_intakeOnOffSubsystem = intakeSubsystem;

		addCommands(new IndexFrontShootCommand(indexMotorSubsystem, intakeSubsystem),
				new ConditionalCommand(new WaitCommand(3), new WaitCommand(0),
						indexerSensorSubsystem::allFrontSensorsOff),
				new IndexBackShootCommand(indexMotorSubsystem, intakeSubsystem), new WaitCommand(2));
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
