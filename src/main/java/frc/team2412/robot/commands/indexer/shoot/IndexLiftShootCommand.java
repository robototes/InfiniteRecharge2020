/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;
// import jdk.internal.jshell.tool.resources.l10n;

public class IndexLiftShootCommand extends CommandBase {
	/**
	 * Creates a new IndexLiftShootCommand.
	 */

	private IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

	public IndexLiftShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		addRequirements(indexerSubsystemSuperStructure);
		// Use addRequirements() here to declare subsystem dependencies.
		this.m_IndexerSubsystemSuperStructure = indexerSubsystemSuperStructure;
	}
	// Called when the command is initially scheduled.
	boolean side;
	@Override
	public void initialize() {
		side = m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontMidSensorValue() || m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontSensorValue();
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		// if(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontMidSensorValue() || m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontSensorValue()){
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(0.1);
		// } else if(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackMidSensorValue() || m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackSensorValue()){
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(0.1);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.3);
		// }else{
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.3);
		// }
		if(side){
			m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(0.1);
		}else{
			m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.3);
		}
		m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
		m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0.6);
		// if(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontInnerSensorValue()&&m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackInnerSensorValue()){
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.2);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(0.2);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0.3);
		// }else if(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontMidSensorValue()){
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(0);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0.3);
		// }else if(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackMidSensorValue()){
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(0);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0.3);
		// }else{
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
		// 	m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0);
		// }
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// m_IndexerSubsystemSuperStructure.setAllSubsystemsToZero();

		m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().stop();
		m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().stop();
		m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().stop();

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
