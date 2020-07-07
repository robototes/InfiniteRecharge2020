package frc.team2412.robot.commands.indexer;

import static frc.team2412.robot.subsystems.constants.IndexerConstants.VALID_SENSOR_BITS;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import frc.team2412.robot.subsystems.constants.IndexerConstants.IndexCommandEntry;
import frc.team2412.robot.subsystems.constants.IndexerConstants.IndexDirection;
import frc.team2412.robot.subsystems.constants.IntakeConstants;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeDirection;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexBitmapCommand extends CommandBase {

	private final IndexerSubsystemSuperStructure m_indexerSubsystem;
	private static IntakeConstants.IntakeDirection s_lastIntakeDirection = IntakeConstants.IntakeDirection.NONE;
	private final IntakeMotorSubsystem m_intakeMotorSubsystem;
	private double lastIndexRunTimeMicroSec = 0.0;

	public IndexBitmapCommand(final IndexerSubsystemSuperStructure indexerMotorSubsystem,
			final IntakeMotorSubsystem intakeMotorSubsystem) {
		m_indexerSubsystem = indexerMotorSubsystem;
		m_intakeMotorSubsystem = intakeMotorSubsystem;

		// Only require the index motor subsystem, as the sensors can be read by
		// multiple commands simultaneously without issues
		addRequirements(indexerMotorSubsystem);
	}

	@Override
	public void execute() {
		IntakeDirection realIntakeDirection = m_intakeMotorSubsystem.getIntakeDirection();

		final boolean intakeOn = (realIntakeDirection != IntakeDirection.NONE);

		final IntakeDirection intakeDirection = getIntakeDirection(realIntakeDirection);

		int sensorBitmap = getSensorBitmap(intakeDirection);

		IndexCommandEntry indexCommand = null;
		for (final IndexCommandEntry entry : IndexCommandEntry.values()) {
			if (entry.expectedBits == (sensorBitmap & entry.validBits)) {
				indexCommand = entry;
			}
		}

		// System.out.println(
		// "Sensor values: " + Integer.toBinaryString(sensorBitmap) +
		// ", Index command: " + indexCommand.ordinal() +
		// ", Intake on: " + intakeOn +
		// ", Intake dir: " + intakeDirection.ordinal());

		if (indexCommand != null) {
			final IndexDirection frontIndexDirection = indexCommand.getFrontIndexDirection(intakeOn);
			final IndexDirection backIndexDirection = indexCommand.getBackIndexDirection(intakeOn);
			boolean runLift = shouldRunLift(frontIndexDirection, backIndexDirection);

			if (runLift) {
				lastIndexRunTimeMicroSec = RobotController.getFPGATime();
			} else if (RobotController.getFPGATime() < lastIndexRunTimeMicroSec
					+ 1000 * IndexerConstants.LIFT_DOWN_MS_DURATION_AFTER_SHUFFLE) {
				runLift = true;
			}

			m_indexerSubsystem.getIndexerMotorLiftSubsystem()
					.set(runLift ? IndexerConstants.LIFT_DOWN_SPEED_FOR_INDEX : 0.0);

			switch (intakeDirection) {
			case BOTH:
			case FRONT:
				m_indexerSubsystem.getIndexerMotorFrontSubsystem().set(getIndexerMotorSpeed(frontIndexDirection));
				m_indexerSubsystem.getIndexerMotorBackSubsystem().set(getIndexerMotorSpeed(backIndexDirection));
				break;
			case BACK:
				// Swap the front & back motor values since the IndexCommandEntry assumes intake
				// from the front
				m_indexerSubsystem.getIndexerMotorFrontSubsystem().set(getIndexerMotorSpeed(backIndexDirection));
				m_indexerSubsystem.getIndexerMotorBackSubsystem().set(getIndexerMotorSpeed(frontIndexDirection));
				break;
			default:
				m_indexerSubsystem.getIndexerMotorFrontSubsystem().set(0);
				m_indexerSubsystem.getIndexerMotorBackSubsystem().set(0);
				break;
			}
		} else {
			m_indexerSubsystem.getIndexerMotorFrontSubsystem().set(0);
			m_indexerSubsystem.getIndexerMotorBackSubsystem().set(0);
			m_indexerSubsystem.getIndexerMotorLiftSubsystem().set(0);
		}
	}

	private boolean shouldRunLift(IndexDirection frontIndexDirection, IndexDirection backIndexDirection) {
		return frontIndexDirection == IndexDirection.IN || frontIndexDirection == IndexDirection.OUT
				|| backIndexDirection == IndexDirection.IN || backIndexDirection == IndexDirection.OUT;
	}

	@Override
	public boolean isFinished() {
		// Command is never finished, as it's intended to be used as the default command
		// for the index motor subsystem
		return false;
	}

	private IntakeDirection getIntakeDirection(IntakeDirection realIntakeDirection) {
		if (realIntakeDirection == IntakeDirection.NONE) {
			if (s_lastIntakeDirection == IntakeDirection.BACK) {
				return IntakeDirection.BACK;
			} else {
				return IntakeDirection.FRONT;
			}
		} else {
			s_lastIntakeDirection = realIntakeDirection;
			return realIntakeDirection;
		}
	}

	private int getSensorBitmap(IntakeDirection intakeDirection) {
		int sensorBitmap = 0;
		switch (intakeDirection) {
		case BOTH:
		case FRONT:
			sensorBitmap = (m_indexerSubsystem.getIndexerSensorSubsystem().getSensorBitmapFrontLSB()
					& VALID_SENSOR_BITS);
			break;
		case BACK:
			sensorBitmap = (m_indexerSubsystem.getIndexerSensorSubsystem().getSensorBitmapBackLSB()
					& VALID_SENSOR_BITS);
			break;
		case NONE:
			assert (false);
			break;
		}
		return sensorBitmap;
	}

	private double getIndexerMotorSpeed(IndexDirection direction) {
		switch (direction) {
		case IN:
			return -IndexerConstants.MAX_SPEED;
		case OUT:
			return IndexerConstants.MAX_SPEED;
		case OFF:
		default:
			return 0;
		}
	}
}