package frc.team2412.robot;

public class RobotMapConstants {

	public final static boolean CLIMB_CONNECTED = false;
	public final static boolean CONTROL_PANEL_CONNECTED = false;
	public final static boolean SHOOTER_CONNECTED = true;
	public final static boolean INDEX_CONNECTED = true;
	public final static boolean INTAKE_CONNECTED = true;
	public final static boolean LIFT_CONNECTED = true;
	public final static boolean DRIVE_BASE_CONNECTED = true;

	public static enum CANBus {
		INTAKE1(11), INDEX1(12), INTAKE2(21), INDEX2(22), INTAKE3(31), INDEX3(32), INDEX_LEFT_MID(40),
		INDEX_RIGHT_MID(41), DRIVE_LEFT_FRONT(3), DRIVE_LEFT_BACK(4), DRIVE_RIGHT_FRONT(1), DRIVE_RIGHT_BACK(2),
		CLIMB1(5), CLIMB2(6), TURRET(7), FLYWHEEL_LEFT(8), FLYWHEEL_RIGHT(9), CONTROL_PANEL(10);

		public final int id;

		private CANBus(int canBusId) {
			id = canBusId;
		}
	}

	public static enum PneumaticPort {
		DRIVE(2), CLIMB_LEFT(4), CLIMB_RIGHT(5), INTAKE_FRONT_UP(7), INTAKE_BACK_UP(6), LIFT_UP(0), LIFT_DOWN(1);

		public final int id;

		private PneumaticPort(int pneumaticPortId) {
			id = pneumaticPortId;
		}
	}

	public static enum DIOPort {
		FRONT_SENSOR(5), FRONT_MID_SENSOR(6), FRONT_INNER_SENSOR(7), BACK_INNER_SENSOR(3), BACK_MID_SENSOR(2),
		BACK_SENSOR(1), INTAKE_BACK_SENSOR(0), INTAKE_FRONT_SENSOR(4);

		public final int id;

		private DIOPort(int dioPortId) {
			id = dioPortId;
		}
	}

	public static enum PWMPort {
		HOOD_SERVO_1(0), HOOD_SERVO_2(1);

		public final int id;

		private PWMPort(int pwmPortId) {
			id = pwmPortId;
		}
	}

	// CHOICE FOR INDEX/INTAKE MODULE
	public static enum IndexIntakeModule {
		ONE(CANBus.INTAKE1.id, CANBus.INDEX1.id), TWO(CANBus.INTAKE2.id, CANBus.INDEX2.id),
		THREE(CANBus.INTAKE3.id, CANBus.INDEX3.id);

		private final int indexCANID;
		private final int intakeCANID;

		private IndexIntakeModule(int intakeID, int indexID) {
			indexCANID = indexID;
			intakeCANID = intakeID;
		}

		public int getIntakeCANID() {
			return intakeCANID;
		}

		public int getIndexCANID() {
			return indexCANID;
		}
	}

}
