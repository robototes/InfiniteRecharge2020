package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexSpitFrontCommand extends CommandBase {

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;
	private IntakeMotorSubsystem m_intakeOnOffSubsystem;
	private boolean isUsingLift;
	public IndexSpitFrontCommand(IndexerSubsystemSuperStructure motorSubsystem, IntakeMotorSubsystem intakeOnOffSubsystem, boolean useLift) {
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		isUsingLift = useLift;
		addRequirements(motorSubsystem, intakeOnOffSubsystem);
	}

	@Override
	public void execute() {
		//m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().out();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().out();
		// Stop jamming?
		if(isUsingLift) {
			m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().set(-0.2);
		}
		m_intakeOnOffSubsystem.frontIntakeOut();
	}

	@Override
	public void end(boolean cancel) {
		//m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().stop();
		m_intakeOnOffSubsystem.intakeOff();
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
