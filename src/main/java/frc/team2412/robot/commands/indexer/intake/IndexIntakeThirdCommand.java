package frc.team2412.robot.commands.indexer.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeThirdCommand extends CommandBase{
	
	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	
	public IndexIntakeThirdCommand(IndexerMotorSubsystem indexSideMotors, IndexerLiftMotorSubsystem indexMidMotor, IndexerSensorSubsystem indexSensors) {
		m_indexerMotorSubsystem  = indexSideMotors;
		m_indexerLiftMotorSubsystem = indexMidMotor;
		m_indexerSensorSubsystem = indexSensors;
		addRequirements(indexSideMotors, indexMidMotor, indexSensors);
	}
	/*
	 * loads ball into center
	 */
	private double speed = 1.0;
	private frc.team2412.robot.RobotState.IntakeDirection t;
	
	public void execute() {
		if(RobotState.getBallCount() == 2) {
			t = RobotState.getintakeDirection();
			//intake ball to position three(center) from front
			if(/*intake direction == front*/ t == RobotState.IntakeDirection.FRONT) {
				while(!(m_indexerSensorSubsystem.getIndexBackInnerSensorValue() && m_indexerSensorSubsystem.getIndexFrontInnerSensorValue())) {
					m_indexerMotorSubsystem.setFrontMotor(-speed); //rotate to back
					m_indexerLiftMotorSubsystem.setMidMotor(-speed); //down
					m_indexerMotorSubsystem.setBackMotor(-speed); // rotate to back
				}
				//stop all
				m_indexerMotorSubsystem.setFrontMotor(0);
				m_indexerLiftMotorSubsystem .setMidMotor(0);
				m_indexerMotorSubsystem.setBackMotor(0);
			}
			//intake ball to position three(center) from back
			else if(/*intake direction == back*/t == RobotState.IntakeDirection.BACK) {
				while(!(m_indexerSensorSubsystem.getIndexBackInnerSensorValue() && m_indexerSensorSubsystem.getIndexFrontInnerSensorValue())) {
					m_indexerMotorSubsystem.setBackMotor(speed); //rotate to front
					m_indexerLiftMotorSubsystem.setMidMotor(speed); //down
					m_indexerMotorSubsystem.setFrontMotor(speed); //rotate to front
				}
				//stop all
				m_indexerMotorSubsystem.setBackMotor(0);
				m_indexerLiftMotorSubsystem.setMidMotor(0);
				m_indexerMotorSubsystem.setFrontMotor(0);
			}
		}
	}
}
