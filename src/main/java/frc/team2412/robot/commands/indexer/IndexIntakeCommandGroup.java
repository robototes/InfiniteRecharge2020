package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFirstCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFithCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeFourthCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeSecondCommand;
import frc.team2412.robot.commands.indexer.intake.IndexIntakeThirdCommand;

public class IndexIntakeCommandGroup extends ParallelCommandGroup {
	
	public IndexIntakeFirstCommand m_indexIntakeOneCommand;
	public IndexIntakeSecondCommand m_indexIntakeTwoCommand;
	public IndexIntakeThirdCommand m_indexIntakeThreeCommand;
	public IndexIntakeFourthCommand m_indexIntakeFourCommand;
	public IndexIntakeFithCommand m_indexIntakeFiveCommand;
	
	public IndexIntakeCommandGroup(IndexIntakeFirstCommand intakeOne, IndexIntakeSecondCommand intakeTwo, IndexIntakeThirdCommand intakeThree,
			IndexIntakeFourthCommand intakeFour, IndexIntakeFithCommand intakeFive) {
		
		m_indexIntakeOneCommand = intakeOne;
		m_indexIntakeTwoCommand = intakeTwo;
		m_indexIntakeThreeCommand = intakeThree;
		m_indexIntakeFourCommand = intakeFour;
		m_indexIntakeFiveCommand = intakeFive;
		
		SequentialCommandGroup IndexIntake = new SequentialCommandGroup(intakeOne, intakeTwo, intakeThree, intakeFour, intakeFive);
		
	}
}
