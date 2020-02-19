package frc.team2412.robot.commands.indexer;

import static frc.team2412.robot.subsystems.constants.IndexerConstants.numBalls;
import static frc.team2412.robot.subsystems.constants.IndexerConstants.unbalancedSide;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;

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
		if (!m_indexerSensorSubsystem.getIndexMidSensorValue()) {
			if (unbalancedSide == IndexerConstants.UnbalancedSide.FRONT) {
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
	public boolean isFinished() {
		if (m_indexerSensorSubsystem.allInnerSensorsOff()) {
			m_indexerMotorSubsystem.stopAllMotors();
			numBalls = 0;
			return true;

		} else {
			numBalls = m_indexerSensorSubsystem.totalSensorsOn();
			return false;
		}

	}

}
