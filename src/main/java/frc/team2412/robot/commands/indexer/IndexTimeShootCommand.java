/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.index.*;

public class IndexTimeShootCommand extends CommandBase {
	/**
	 * Creates a new IndexLiftShootCommand.
	 */

	private IndexerMotorFrontSubsystem mf;
	private IndexerMotorBackSubsystem mb;
	private IndexerMotorLiftSubsystem ml;
	private IndexerSensorSubsystem ms;
	Timer t;

	public IndexTimeShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		addRequirements(indexerSubsystemSuperStructure);
		// Use addRequirements() here to declare subsystem dependencies.
		mf=indexerSubsystemSuperStructure.getIndexerMotorFrontSubsystem();
		mb=indexerSubsystemSuperStructure.getIndexerMotorBackSubsystem();
		ml=indexerSubsystemSuperStructure.getIndexerMotorLiftSubsystem();
		ms=indexerSubsystemSuperStructure.getIndexerSensorSubsystem();
		t=new Timer();
	}
	public static final double TIME = 1;
	@Override
	public void initialize() {
		t.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		mf.set(t.get()<TIME ? -0.5 : ms.getIndexFrontSensorValue() ? 0 : 0.1);
		mb.set(t.get()>TIME ? -0.5 : ms.getIndexBackSensorValue() ? 0 : 0.1);
		ml.set(0.6);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return t.get()>TIME*2;
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// m_IndexerSubsystemSuperStructure.setAllSubsystemsToZero();
		mf.stop();
		mb.stop();
		ml.stop();
		t.reset();

	}

}
