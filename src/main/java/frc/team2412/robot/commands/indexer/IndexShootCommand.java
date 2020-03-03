package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem, IntakeOnOffSubsystem intakeSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeOnOffSubsystem = intakeSubsystem;
		m_indexerMotorSubsystem.setLifting(true);
		addCommands(new IndexFrontShootCommand(sensorSubsystem, motorSubsystem, intakeSubsystem), new WaitCommand(3),
				new IndexBackShootCommand(sensorSubsystem, motorSubsystem, intakeSubsystem), new WaitCommand(2));
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.setLifting(false);
		m_indexerMotorSubsystem.stopAllMotors();
		m_intakeOnOffSubsystem.setIntake(0);
	}
}
