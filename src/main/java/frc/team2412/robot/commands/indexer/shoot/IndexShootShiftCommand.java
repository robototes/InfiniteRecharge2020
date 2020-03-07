package frc.team2412.robot.commands.indexer.shoot;

import java.util.Map;
import static java.util.Map.entry;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexShootShiftCommand extends SelectCommand {

	private static enum ShiftSelect {
		FRONT, BACK, DEFAULT;
	}

	private static class ShiftSelector {
		private IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

		public ShiftSelector(IndexerSubsystemSuperStructure indexerSubsystemSuperStructure) {
			this.m_IndexerSubsystemSuperStructure = indexerSubsystemSuperStructure;
		}

		public ShiftSelect getSideToShift() {
			if (!m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackSensorValue()) {
				return ShiftSelect.BACK;
			} else if (!m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackSensorValue()) {
				return ShiftSelect.FRONT;
			}

			return ShiftSelect.DEFAULT;
		}

	}

	public IndexShootShiftCommand(IndexerSubsystemSuperStructure indexSubsystemSuperStructure) {
		super(Map.ofEntries(
				entry(ShiftSelect.BACK,
						new IndexShiftBackCommand(indexSubsystemSuperStructure)
								.andThen(new IndexShootFrontThenBack(indexSubsystemSuperStructure))),
				entry(ShiftSelect.FRONT,
						new IndexShiftFrontCommand(indexSubsystemSuperStructure)
								.andThen(new IndexShootFrontThenBack(indexSubsystemSuperStructure))),
				entry(ShiftSelect.DEFAULT,
						new IndexShiftBackCommand(indexSubsystemSuperStructure)
								.andThen(new IndexShootFrontThenBack(indexSubsystemSuperStructure)))),
				new ShiftSelector(indexSubsystemSuperStructure)::getSideToShift);

	}

}
