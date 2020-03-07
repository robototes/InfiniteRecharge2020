package frc.team2412.robot.commands.indexer.shoot;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootLargestCommand extends SequentialCommandGroup {

	private IndexerSubsystemSuperStructure m_indexerSuperStructure;

	public IndexShootLargestCommand(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
		this.m_indexerSuperStructure = indexerSubsystemSuperStructure;

		this.addCommands(new ConditionalCommand(new IndexShootFrontThenBack(indexerSubsystemSuperStructure),
				new IndexShootBackThenFront(indexerSubsystemSuperStructure), this::isFrontLargestSide));

	}

	public boolean isFrontLargestSide() {
		return m_indexerSuperStructure.getIndexerSensorSubsystem().getSensorBitmapFrontLSB() <= m_indexerSuperStructure
				.getIndexerSensorSubsystem().getSensorBitmapBackLSB();
	}

}
