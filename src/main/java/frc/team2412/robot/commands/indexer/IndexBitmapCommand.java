package frc.team2412.robot.commands.indexer;

import static frc.team2412.robot.subsystems.constants.IndexerConstants.VALID_SENSOR_BITS;

import java.util.Arrays;

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
		final IntakeDirection intakeDirection;
		final boolean intakeOn = (realIntakeDirection != IntakeDirection.NONE);
		if (realIntakeDirection == IntakeDirection.NONE) {
			if (s_lastIntakeDirection == IntakeDirection.BACK) {
				intakeDirection = IntakeDirection.BACK;
			} else {
				intakeDirection = IntakeDirection.FRONT;
			}
		} else {
			intakeDirection = realIntakeDirection;
			s_lastIntakeDirection = realIntakeDirection;
		}

		int sensorBitmap = getSensorBitmap(intakeDirection);

		Arrays.stream(IndexCommandEntry.values()) // loop over all the different IndexCommandEntries
				.filter(c -> c.expectedBits == (sensorBitmap & c.validBits))
				// Remove the commands that dont meet this condition
				.findAny()
				// Find one of the filtered commands
				.ifPresentOrElse(command -> runCommandPresent(command, intakeDirection, intakeOn),
						m_indexerSubsystem::setAllSubsystemsToZero);
		// Run the code if the command exists. If the command doesnt exist turn all of
		// index off
	}

	private void runCommandPresent(IndexCommandEntry indexCommand, IntakeDirection intakeDirection,
			final boolean intakeOn) {
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
			m_indexerSubsystem.setFrontAndBack(getIndexerMotorSpeed(frontIndexDirection),
					getIndexerMotorSpeed(backIndexDirection));
			break;
		case BACK:
			// Swap the front & back motor values since the IndexCommandEntry assumes intake
			// from the front
			m_indexerSubsystem.setFrontAndBack(getIndexerMotorSpeed(backIndexDirection),
					getIndexerMotorSpeed(frontIndexDirection));
			break;
		default:
			m_indexerSubsystem.setFrontAndBack(0, 0);
			break;
		}
	}

	private boolean shouldRunLift(final IndexDirection frontIndexDirection, final IndexDirection backIndexDirection) {
		return ((frontIndexDirection == IndexDirection.IN) || (frontIndexDirection == IndexDirection.OUT))
				|| ((backIndexDirection == IndexDirection.IN) || (backIndexDirection == IndexDirection.OUT));
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
		default:
			assert (false);
			break;
		}
		return sensorBitmap;
	}

	@Override
	public boolean isFinished() {
		// Command is never finished, as it's intended to be used as the default command
		// for the index motor subsystem
		return false;
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