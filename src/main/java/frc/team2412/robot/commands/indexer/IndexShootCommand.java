package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends CommandBase {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		addRequirements(sensorSubsystem, motorSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setMidMotor(1);
		if (!m_indexerSensorSubsystem.getIndexBackInnerSensorValue()
				&& !m_indexerSensorSubsystem.getIndexFrontInnerSensorValue()) {
			if (RobotState.m_unbalancedSide == RobotState.UnbalancedSide.FRONT) {
				m_indexerMotorSubsystem.setFrontMotor(-1);
				if (m_indexerSensorSubsystem.allFrontSensorsOff()) {
					m_indexerMotorSubsystem.setBackMotor(-1);
				}
			} else {
				m_indexerMotorSubsystem.setBackMotor(-1);
				if (m_indexerSensorSubsystem.allBackSensorsOff()) {
					m_indexerMotorSubsystem.setFrontMotor(-1);
				}
			}
		}
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.stopAllMotors();
		RobotState.m_ballCount = 0;
	}

	@Override
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.allInnerSensorsOff()) {
			return true;

		} else {
			RobotState.m_ballCount = m_indexerSensorSubsystem.totalSensorsOn();
			return false;
		}

	}

}
