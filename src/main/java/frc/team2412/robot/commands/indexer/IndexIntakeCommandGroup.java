package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFirstCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFithCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFourthCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeSecondCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeThirdCommand;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeCommandGroup extends ParallelCommandGroup {
	
	public IndexIntakeFirstCommand m_indexIntakeOneCommand;
	public IndexIntakeSecondCommand m_indexIntakeTwoCommand;
	public IndexIntakeThirdCommand m_indexIntakeThreeCommand;
	public IndexIntakeFourthCommand m_indexIntakeFourCommand;
	public IndexIntakeFithCommand m_indexIntakeFiveCommand;
	
	public IndexIntakeCommandGroup(IndexerMotorSubsystem motors, IndexerLiftMotorSubsystem liftMotor, IndexerSensorSubsystem sensors) {
		
		m_indexIntakeOneCommand = new IndexIntakeFirstCommand(motors, sensors);
		m_indexIntakeTwoCommand = new IndexIntakeSecondCommand(motors, sensors);
		m_indexIntakeThreeCommand = new IndexIntakeThirdCommand(motors, liftMotor, sensors);
		m_indexIntakeFourCommand = new IndexIntakeFourthCommand(motors, liftMotor, sensors);
		m_indexIntakeFiveCommand = new IndexIntakeFithCommand(motors, liftMotor, sensors);
		
		SequentialCommandGroup IndexIntake = new SequentialCommandGroup(m_indexIntakeOneCommand, m_indexIntakeTwoCommand,
				m_indexIntakeThreeCommand, m_indexIntakeFourCommand, m_indexIntakeFiveCommand);
		
	}
}
