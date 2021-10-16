/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexLiftShootSlowCommand extends CommandBase {
	/**
	 * Creates a new IndexLiftShootCommand.
	 */

	private IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

	public IndexLiftShootSlowCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		// Use addRequirements() here to declare subsystem dependencies.
		this.m_IndexerSubsystemSuperStructure = indexerSubsystemSuperStructure;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_IndexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem().set(0.3);
		m_IndexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem().set(-0.3);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// m_IndexerSubsystemSuperStructure.getIndexerMotorBackSubsystem().stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
