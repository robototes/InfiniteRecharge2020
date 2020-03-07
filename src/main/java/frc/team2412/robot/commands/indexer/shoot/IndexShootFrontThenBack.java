package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootFrontThenBack extends SequentialCommandGroup {

	public IndexShootFrontThenBack(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		this.addCommands(new IndexShootFrontCommand(indexerSubsystemSuperStructure),
				new IndexShootBackCommand(indexerSubsystemSuperStructure));
	}

}
