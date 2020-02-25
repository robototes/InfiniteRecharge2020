package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexSpitCommand extends CommandBase {

	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IndexSpitCommand(IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem motorSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		m_indexerSensorSubsystem = sensorSubsystem;
		m_indexerMotorSubsystem = motorSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;

		addRequirements(sensorSubsystem, motorSubsystem, intakeOnOffSubsystem);
	}

	@Override
	public void execute() {
		m_indexerMotorSubsystem.setFrontMotor(1);
		m_indexerMotorSubsystem.setBackMotor(1);
		m_indexerMotorSubsystem.setMidMotor(-0.1);
		m_intakeOnOffSubsystem.setIntake(1);
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.stopAllMotors();
		m_intakeOnOffSubsystem.intakeOff();
		RobotState.m_ballCount = 0;
	}

	@Override
	public boolean isFinished() {
		return m_indexerSensorSubsystem.allBackSensorsOff() && m_indexerSensorSubsystem.allFrontSensorsOff();
	}

}
