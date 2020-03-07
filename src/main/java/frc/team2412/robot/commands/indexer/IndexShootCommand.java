package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.indexer.shoot.IndexShootSelectionCommand;
import frc.team2412.robot.commands.intake.IntakeBothUpCommand;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootCommand extends SequentialCommandGroup {

	public IndexShootCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure,
			IntakeUpDownSubsystem intakeUpDownSubsystem) {

		this.addCommands(new IntakeBothUpCommand(intakeUpDownSubsystem),
				new IndexShootSelectionCommand(indexerSubsystemSuperStructure),
				new IndexPulseCommand(indexerSubsystemSuperStructure),
				new IndexShootSelectionCommand(indexerSubsystemSuperStructure)); // Are we assuming that we'll only have
																					// to pulse once to find all the
																					// missing balls?

	}

}
