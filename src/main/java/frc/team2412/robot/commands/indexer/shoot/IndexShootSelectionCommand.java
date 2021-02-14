package frc.team2412.robot.commands.indexer.shoot;

import static java.util.Map.entry;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootSelectionCommand extends SelectCommand {

	private static enum ShootSelection {
		LARGEST, SHIFT, BACK, FRONT;
	}

	private static class ShootSelector {
		private IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure;

		public ShootSelector(IndexerSubsystemSuperStructure m_IndexerSubsystemSuperStructure) {
			this.m_IndexerSubsystemSuperStructure = m_IndexerSubsystemSuperStructure;
		}

		public ShootSelection getShooterSelection() {
			if (!(m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexFrontInnerSensorValue()
					|| m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackInnerSensorValue())) {
				return ShootSelection.LARGEST;
			} else if (m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().allInnerSensorsOn()) {
				return ShootSelection.SHIFT;
			} else if (m_IndexerSubsystemSuperStructure.getIndexerSensorSubsystem().getIndexBackInnerSensorValue()) {
				return ShootSelection.BACK;
			}
			return ShootSelection.FRONT;
		}
	}

	private IndexerSubsystemSuperStructure m_indexerMotorSubsystem;

	public IndexShootSelectionCommand(IndexerSubsystemSuperStructure indexSubsystemSuperStructure) {
		super(Map.ofEntries(entry(ShootSelection.LARGEST, new IndexShootLargestCommand(indexSubsystemSuperStructure)),
				entry(ShootSelection.SHIFT, new IndexShootShiftCommand(indexSubsystemSuperStructure)),
				entry(ShootSelection.BACK, new IndexShootBackThenFront(indexSubsystemSuperStructure)),
				entry(ShootSelection.FRONT, new IndexShootFrontThenBack(indexSubsystemSuperStructure))),
				new ShootSelector(indexSubsystemSuperStructure)::getShooterSelection);

		m_indexerMotorSubsystem = indexSubsystemSuperStructure;
	}

	@Override
	public void end(boolean cancel) {
		m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop();
		m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().stop();
	}
}
