package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexSwitchFourCommand extends CommandBase {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexSwitchFourCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		if (m_indexerSensorSubsystem.getIntakeFrontSensorValue()
				|| m_indexerSensorSubsystem.getIntakeBackSensorValue()) {
			if (RobotState.m_unbalancedSide == RobotState.UnbalancedSide.FRONT) {
				m_indexerMotorSubsystem.setFrontMotor(-1);
				m_indexerMotorSubsystem.setBackMotor(1);
			} else {
				m_indexerMotorSubsystem.setFrontMotor(1);
				m_indexerMotorSubsystem.setBackMotor(-1);
			}
		}

	}

	@Override
	public boolean isFinished() {
		if (RobotState.m_unbalancedSide == RobotState.UnbalancedSide.FRONT) {
			if (m_indexerSensorSubsystem.getIndexBackSensorValue()) {
				m_indexerMotorSubsystem.stopFrontPID(IndexerConstants.NORMAL_STOP_DISTANCE);
				m_indexerMotorSubsystem.stopBackPID(IndexerConstants.NORMAL_STOP_DISTANCE);
				RobotState.m_unbalancedSide = RobotState.flip(RobotState.m_unbalancedSide);
				return true;
			} else {
				return false;
			}
		} else {
			if (m_indexerSensorSubsystem.getIndexFrontSensorValue()) {
				m_indexerMotorSubsystem.stopFrontPID(IndexerConstants.NORMAL_STOP_DISTANCE);
				m_indexerMotorSubsystem.stopBackPID(IndexerConstants.NORMAL_STOP_DISTANCE);
				RobotState.m_unbalancedSide = RobotState.flip(RobotState.m_unbalancedSide);
				return true;
			} else {
				return false;
			}
		}

	}

}
