/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.index.*;

public class IndexNewShootCommand extends CommandBase {
	/**
	 * Creates a new IndexLiftShootCommand.
	 */

	private IndexerMotorFrontSubsystem mf;
	private IndexerMotorBackSubsystem mb;
	private IndexerMotorLiftSubsystem ml;
	private IndexerSensorSubsystem ms;

	public IndexNewShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		addRequirements(indexerSubsystemSuperStructure);
		// Use addRequirements() here to declare subsystem dependencies.
		mf=indexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem();
		mb=indexerSubsystemSuperStructure.getIndexerMotorBackSubsystem();
		ml=indexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem();
		ms=indexerSubsystemSuperStructure.getIndexerSensorSubsystem();
	}
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		 if(!ms.allFrontSensorsOff()){
			mf.set(-0.3);
			if(!ms.getIndexBackSensorValue()) mb.set(0.2);
		}else if(!ms.allBackSensorsOff()){
			mb.set(-0.3);
			if(!ms.getIndexFrontSensorValue()) mf.set(0.2);
		}else{
			mf.set(-0.3);
			mb.set(-0.3);
		}
		ml.set(0.6);

	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// m_IndexerSubsystemSuperStructure.setAllSubsystemsToZero();
		mf.stop();
		mb.stop();
		ml.stop();

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
