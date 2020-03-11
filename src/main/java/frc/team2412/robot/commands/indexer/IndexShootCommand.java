package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftShootCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftStopCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexShootSelectionCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;
import frc.team2412.robot.subsystems.intake.IntakeBackPneumaticSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontPneumaticSubsystem;

public class IndexShootCommand extends SequentialCommandGroup {

	public IndexShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {

		this.addCommands(
				new IndexShootSelectionCommand(indexerSubsystemSuperStructure),
				new IndexPulseCommand(indexerSubsystemSuperStructure),
				new IndexShootSelectionCommand(indexerSubsystemSuperStructure),
				new IndexLiftStopCommand(indexerSubsystemSuperStructure)); // Are we assuming that we'll only have
		// to pulse once to find all the
		// missing balls? Yes

	}

}
