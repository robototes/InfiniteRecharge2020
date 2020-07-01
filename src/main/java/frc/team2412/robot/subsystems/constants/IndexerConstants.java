package frc.team2412.robot.subsystems.constants;

public class IndexerConstants {

	public enum UnbalancedSide {
		FRONT, BACK;

		public void flip() {
			UnbalancedSide s = this;
			switch (s) {
				case FRONT:
					s = UnbalancedSide.BACK;
					break;
				case BACK:
					s = UnbalancedSide.FRONT;
					break;
			}
		}
	}

	// PID FOR AFTER A SENSOR PICKS UP A BALL
	public static final double PID_P = 0.03;
	public static final double PID_I = 0;
	public static final double PID_D = 0;

	// RATIO
	// GEARBOX = 5:1, SPINDLE DIA = 1.2

	// FOR OUTER SENSORS
	public static final double NORMAL_STOP_DISTANCE = 3;

	// FOR INNER SENSORS
	public static final double SHORT_STOP_DISTANCE = 2;
	public static final double LONG_STOP_DISTANCE = 5;

	// FOR MIDDLE
	public static final double EXTRA_LONG_STOP_DISTANCE = 10;

	public static final double SPINDLE_DIAMETER = 1.2;
	public static final double INCH_STOP_DISTANCE = 2;

	public static final double MAX_SPEED = 0.3;

	public static final int LIFT_DOWN_MS_DURATION_AFTER_SHUFFLE = 250;
	public static final double LIFT_DOWN_SPEED_FOR_INDEX = 0.1;
	public static final double MAX_LIFT_SPEED = 0.75;
	public static final double TOP_TICKS = 20;
	public static final double BOTTOM_TICKS = 0;
	public static final double SHIFT_DISTANCE = 25;

	public static final int VALID_SENSOR_BITS = 0b111111;

	public static enum IndexDirection {
		IN, OUT, OFF, DISABLED
	}

	public static enum IndexCommandEntry {
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

}
