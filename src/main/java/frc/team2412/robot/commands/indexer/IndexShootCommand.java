package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftShootCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftStopCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexShootBackCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexShootSelectionCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootCommand extends SequentialCommandGroup {

	public IndexShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		addRequirements(indexerSubsystemSuperStructure);
		this.addCommands(new IndexLiftShootCommand(indexerSubsystemSuperStructure),
				//new IndexShootSelectionCommand(indexerSubsystemSuperStructure),
				new IndexShootBackCommand(indexerSubsystemSuperStructure),
				new IndexPulseCommand(indexerSubsystemSuperStructure),
				new IndexShootBackCommand(indexerSubsystemSuperStructure),
				//new IndexShootSelectionCommand(indexerSubsystemSuperStructure),
				new IndexLiftStopCommand(indexerSubsystemSuperStructure)); // Are we assuming that we'll only have
		// to pulse once to find all the
		// missing balls? Yes

	}

}
