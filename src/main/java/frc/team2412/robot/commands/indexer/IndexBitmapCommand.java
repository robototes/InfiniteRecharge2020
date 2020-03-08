package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.IntakeDirection;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IndexBitmapCommand extends CommandBase {
	private final IndexerSubsystemSuperStructure m_indexerSubsystem;
	private static IntakeDirection s_lastIntakeDirection = IntakeDirection.NONE;
	private double lastIndexRunTimeMicroSec = 0.0;

	static enum IndexDirection {
		IN, OUT, OFF, DISABLED
	}

	static final int VALID_SENSOR_BITS = 0b111111;

	static enum IndexCommandEntry {
		// Bit flags Intake Off Intake On
		// Valid expected Front motor Back motor Front motor Back motor

		// Don't move, as there's nothing in the outer slots -or-
		// Back side has inner and outer slots full, front side only has middle slot
		// full
		A(0b100001, 0b000000, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF),
		B(0b101111, 0b101010, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF),

		// Back side has outer slot full and inner slot empty, so move back ball(s) in
		// Front side has outer slot empty and inner or middle slot full, so don't move
		C(0b101111, 0b100010, IndexDirection.OFF, IndexDirection.IN, IndexDirection.OFF, IndexDirection.IN),
		D(0b101101, 0b100100, IndexDirection.OFF, IndexDirection.IN, IndexDirection.OFF, IndexDirection.IN),

		// Back side has outer and inner slots full, so don't move. Front side has inner
		// slot full so disable taking more balls
		E(0b101100, 0b101100, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.DISABLED, IndexDirection.DISABLED),
		// Back side has outer slot full and inner slot empty, so move balls in. Front
		// side has inner slot full so disable taking more balls
		F(0b101101, 0b100101, IndexDirection.OFF, IndexDirection.IN, IndexDirection.DISABLED, IndexDirection.DISABLED),

		// Back side has outer and inner slots full and front side is empty, so move
		// front side balls in if intake is on
		G(0b101111, 0b101000, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.IN, IndexDirection.OFF),

		// Back side has outer slot empty and front side has inner slot empty and outer
		// slot full, so move front side in
		H(0b100101, 0b000001, IndexDirection.IN, IndexDirection.OFF, IndexDirection.IN, IndexDirection.OFF),
		// Back side has inner and outer slots full, and front side has inner slot empty
		// and outer slot full, so move front side in
		J(0b101101, 0b101001, IndexDirection.IN, IndexDirection.OFF, IndexDirection.IN, IndexDirection.OFF),

		// Back side has outer slot full and inner slot open, and front side has inner
		// and middle open. Move back side in, and front side in if intake is on
		K(0b101110, 0b100000, IndexDirection.OFF, IndexDirection.IN, IndexDirection.IN, IndexDirection.IN),

		// Back side has outer slot full and inner slot open, and front side has inner
		// open and middle and outer full. Move both sides in
		L(0b101111, 0b100011, IndexDirection.IN, IndexDirection.IN, IndexDirection.IN, IndexDirection.IN),

		// Back side has outer slot open and front side has inner and outer slots full,
		// so move back side out and front side in
		M(0b100101, 0b000101, IndexDirection.IN, IndexDirection.OUT, IndexDirection.IN, IndexDirection.OUT);

		public final int validBits;
		public final int expectedBits;
		private final IndexDirection intakeOffFrontIndexDir;
		private final IndexDirection intakeOffBackIndexDir;
		private final IndexDirection intakeOnFrontIndexDir;
		private final IndexDirection intakeOnBackIndexDir;

		// Get index motor directions for a given Intake state
		public IndexDirection getFrontIndexDirection(final boolean intakeOn) {
			return intakeOn ? intakeOnFrontIndexDir : intakeOffFrontIndexDir;
		}

		public IndexDirection getBackIndexDirection(final boolean intakeOn) {
			return intakeOn ? intakeOnBackIndexDir : intakeOffBackIndexDir;
		}

		private IndexCommandEntry(final int validBits, final int expectedBits,
				final IndexDirection intakeOffFrontIndexDir, final IndexDirection intakeOffBackIndexDir,
				final IndexDirection intakeOnFrontIndexDir, final IndexDirection intakeOnBackIndexDir) {
			assert ((validBits & expectedBits) == expectedBits);
			this.validBits = validBits;
			this.expectedBits = expectedBits;
			this.intakeOffFrontIndexDir = intakeOffFrontIndexDir;
			this.intakeOffBackIndexDir = intakeOffBackIndexDir;
			this.intakeOnFrontIndexDir = intakeOnFrontIndexDir;
			this.intakeOnBackIndexDir = intakeOnBackIndexDir;
		}
	}

	public IndexBitmapCommand(final IndexerSubsystemSuperStructure indexerMotorSubsystem) {
		m_indexerSubsystem = indexerMotorSubsystem;

		// Only require the index motor subsystem, as the sensors can be read by
		// multiple commands simultaneously without issues
		addRequirements(indexerMotorSubsystem);
	}

	@Override
	public void execute() {
		IntakeDirection intakeDirection = RobotState.getintakeDirection();
		final boolean intakeOn = (intakeDirection != IntakeDirection.NONE);
		if (intakeDirection == IntakeDirection.NONE) {
			if (s_lastIntakeDirection == IntakeDirection.BACK) {
				intakeDirection = IntakeDirection.BACK;
			} else {
				intakeDirection = IntakeDirection.FRONT;
			}
		} else {
			s_lastIntakeDirection = intakeDirection;
		}

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

		IndexCommandEntry indexCommand = null;
		for (final IndexCommandEntry entry : IndexCommandEntry.values()) {
			if (entry.expectedBits == (sensorBitmap & entry.validBits)) {
				indexCommand = entry;
			}
		}

		// System.out.println(
		// 	"Sensor values: " + Integer.toBinaryString(sensorBitmap) +
		// 	", Index command: " + indexCommand.ordinal() +
		// 	", Intake on: " + intakeOn +
		// 	", Intake dir: " + intakeDirection.ordinal());

		if (indexCommand != null) {
			final IndexDirection frontIndexDirection = indexCommand.getFrontIndexDirection(intakeOn);
			final IndexDirection backIndexDirection = indexCommand.getBackIndexDirection(intakeOn);
			boolean runLift = ((frontIndexDirection == IndexDirection.IN) || (frontIndexDirection == IndexDirection.OUT)) ||
			((backIndexDirection == IndexDirection.IN) || (backIndexDirection == IndexDirection.OUT));

			if (runLift) {
				lastIndexRunTimeMicroSec = RobotController.getFPGATime();
			} else if (RobotController.getFPGATime() < lastIndexRunTimeMicroSec + 1000 * 500) {
				runLift = true;
			}
			
			m_indexerSubsystem.getIndexerMotorLiftSubsystem().set(runLift ? IndexerConstants.LIFT_DOWN_SPEED : 0.0);
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