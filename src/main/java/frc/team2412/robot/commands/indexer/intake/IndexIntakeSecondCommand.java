package frc.team2412.robot.commands.indexer.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeSecondCommand extends CommandBase{
	
	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerSensorSubsystem m_indexerSensorSubsystem;

	public IndexIntakeSecondCommand(IndexerMotorSubsystem indexSideMotors, IndexerSensorSubsystem indexSensors) {
		m_indexerMotorSubsystem  = indexSideMotors;
		m_indexerSensorSubsystem = indexSensors;
		addRequirements(indexSideMotors, indexSensors);
	}

	/*
	 * loads ball into midle position between center and intake
	 */
	
	private double speed = 1.0;
	//TODO SET INTAKE DIRECTION IN OI || INTAKE SUBSYSTEM
	private frc.team2412.robot.RobotState.IntakeDirection t;
	
	public void execute() {
		t = RobotState.getintakeDirection();
		if(RobotState.getBallCount() == 1) {
			//intake ball to position five from front
			if(/*intake direction == front*/ t == RobotState.IntakeDirection.FRONT) {
				while(!m_indexerSensorSubsystem.getIndexFrontMidSensorValue())
					m_indexerMotorSubsystem.setFrontMotor(-speed);
				m_indexerMotorSubsystem.setFrontMotor(0);
			}
			//intake ball to position one from back
			else if(/*intake direction == back*/t == RobotState.IntakeDirection.BACK) {
				while(!m_indexerSensorSubsystem.getIndexBackMidSensorValue())
					m_indexerMotorSubsystem.setBackMotor(speed);
				m_indexerMotorSubsystem.setBackMotor(0);
			}	
		}
	}
}
