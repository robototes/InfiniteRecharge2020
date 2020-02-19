package frc.team2412.robot.commands.indexer;

import static frc.team2412.robot.subsystems.constants.IndexerConstants.numBalls;
import static frc.team2412.robot.subsystems.constants.IndexerConstants.unbalancedSide;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.commands.intake.IntakeFrontBothOffCommandGroup;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeFourFiveFrontCommand extends CommandBase {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexIntakeFourFiveFrontCommand(IndexerSensorSubsystem sensorSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		addRequirements(sensorSubsystem);
	}

	@Override
	public void execute() {
		CommandScheduler.getInstance()
				.schedule(new IntakeFrontBothOffCommandGroup(m_intakeUpDownSubsystem, m_intakeOnOffSubsystem));
	}

	@Override
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.getIndexFrontSensorValue()) {
			unbalancedSide = IndexerConstants.UnbalancedSide.FRONT;
			numBalls++;
			return true;
		} else {
			return false;
		}
	}

}
