package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootBackThenFront extends SequentialCommandGroup {

	public IndexShootBackThenFront(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		this.addCommands(new IndexShootBackCommand(indexerSubsystemSuperStructure),
				new IndexShootFrontCommand(indexerSubsystemSuperStructure));
	}

}
