/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.team2412.robot.subsystems.index.*;

import java.util.function.BooleanSupplier;

public class IndexButtonShootCommand extends CommandBase {
	/**
	 * Creates a new IndexLiftShootCommand.
	 */

	private IndexerMotorFrontSubsystem mf;
	private IndexerMotorBackSubsystem mb;
	private IndexerMotorLiftSubsystem ml;
	private Button frontButton, backButton;

	public IndexButtonShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure, Button f, Button b) {
		addRequirements(indexerSubsystemSuperStructure);
		// Use addRequirements() here to declare subsystem dependencies.
		mf=indexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem();
		mb=indexerSubsystemSuperStructure.getIndexerMotorBackSubsystem();
		ml=indexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem();
		frontButton = f;
		backButton = b;
	}
	public IndexButtonShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure, Button b) {
		this(indexerSubsystemSuperStructure, new Button(()->true), b);
	}
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		mf.set(frontButton.get() ? -0.3 : 0);
		mb.set(backButton.get() ? -0.3 : 0);
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
