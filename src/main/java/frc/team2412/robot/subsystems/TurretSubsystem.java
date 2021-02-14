package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.TurretConstants.ENCODER_MAX_ERROR_JUMP;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_DEGREE;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_REVOLUTION;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TURRET_PID_CONTROLLER;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.math.MathUtils;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class TurretSubsystem extends PIDSubsystem {

	private Rotations currentAngle;
	private WPI_TalonSRX motor;

	private LimelightSubsystem limelightSubsystem;

	private int turretOffsetPosition = 0;
	private int turretPastPosition;
	private int turretCurrentPosition;

	public TurretSubsystem(WPI_TalonSRX motor, LimelightSubsystem limelightSubsystem) {
		super(TURRET_PID_CONTROLLER);
		this.motor = motor;
		this.limelightSubsystem = limelightSubsystem;

		initTurretEncoder();
	}

	public Rotations getCurrentAngle() {
		return currentAngle;
	}

	@Override
	public double getMeasurement() {
		return turretCurrentPosition - turretOffsetPosition;
	}

	public void initTurretEncoder() {
		motor.configFactoryDefault();
		motor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
		motor.setSelectedSensorPosition(0);

		motor.setNeutralMode(NeutralMode.Brake);

		turretOffsetPosition = motor.getSelectedSensorPosition(0);
		turretCurrentPosition = 0;
		turretPastPosition = 0;

		periodic();
	}

	@Override
	public void periodic() {
		turretCurrentPosition = motor.getSelectedSensorPosition(0);

		if (turretCurrentPosition - turretPastPosition > ENCODER_MAX_ERROR_JUMP) {
			turretOffsetPosition += TICKS_PER_REVOLUTION;

		} else if (Math.abs(turretCurrentPosition - turretPastPosition) > ENCODER_MAX_ERROR_JUMP) {
			turretCurrentPosition = turretPastPosition;
		}

		turretPastPosition = turretCurrentPosition;
		currentAngle = new Rotations((getMeasurement() == 0) ? 0 : (getMeasurement() / TICKS_PER_DEGREE),
				RotationUnits.DEGREE);
		// System.out.println(getMeasurement());
	}

	public void set(double output) {
		output = MathUtils.constrain(output, -1, 1);

		if (output < 0.05 && output > -0.05) {
			output = 0;
		}

		motor.set(output);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		motor.set(output);
	}

	public double getCurrentDraw() {
		return motor.getStatorCurrent();
	}
}
