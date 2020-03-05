package frc.team2412.robot.commands.indexer.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexIntakeFourthCommand extends CommandBase{
	
	private IndexerMotorSubsystem m_indexerMotorSubsystem;
	private IndexerSensorSubsystem m_indexerSensorSubsystem;
	private IndexerLiftMotorSubsystem m_indexerLiftMotorSubsystem;
	
	public IndexIntakeFourthCommand(IndexerMotorSubsystem indexSideMotors, IndexerLiftMotorSubsystem indexMidMotor, IndexerSensorSubsystem indexSensors) {
		m_indexerMotorSubsystem  = indexSideMotors;
		m_indexerLiftMotorSubsystem = indexMidMotor;
		m_indexerSensorSubsystem = indexSensors;
		addRequirements(indexSideMotors, indexMidMotor, indexSensors);
	}
	/*
	 * loads ball into one position over from center (oppocite the side it was intook)
	 */
	private double speed = 1.0;
	//TODO SET INTAKE DIRECTION IN OI || INTAKE SUBSYSTEM
	private frc.team2412.robot.RobotState.IntakeDirection t;
	
	public void execute() {
		//intake ball to position two from front
		if(/*intake direction == front*/ t == RobotState.IntakeDirection.FRONT) {
			while(!m_indexerSensorSubsystem.getIndexBackMidSensorValue()) {
				m_indexerMotorSubsystem.setFrontMotor(-speed); //rotate to back
				m_indexerLiftMotorSubsystem.setMidMotor(-speed); //down
				m_indexerMotorSubsystem.setBackMotor(-speed); // rotate to back
			}
			//stop all
			m_indexerMotorSubsystem.setFrontMotor(0);
			m_indexerLiftMotorSubsystem .setMidMotor(0);
			m_indexerMotorSubsystem.setBackMotor(0);
		}
		//intake ball to position four from back
		else if(/*intake direction == back*/t == RobotState.IntakeDirection.BACK) {
			while(!m_indexerSensorSubsystem.getIndexFrontMidSensorValue()) {
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
