package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.TurretConstants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.*;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class TurretSubsystem extends PIDSubsystem {

	private Rotations currentAngle;
	private final WPI_TalonSRX motor;

	private final LimelightSubsystem limelightSubsystem;

	private int turretOffsetPosition = 0;
	private int turretPastPosition;
	private int turretCurrentPosition;

	private final static int TURRET_MAX_POSITION = 2500;
	private final static int TURRET_MIN_POSITION = 0;

	public TurretSubsystem(WPI_TalonSRX motor, LimelightSubsystem limelightSubsystem) {
		super(TURRET_PID_CONTROLLER);
		this.motor = motor;
		this.limelightSubsystem = limelightSubsystem;

		this.motor.configContinuousCurrentLimit(20, 500);
		this.motor.configPeakCurrentLimit(40, 100);
		this.motor.enableCurrentLimit(true);
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
//		motor.configFactoryDefault();
//		motor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
//		motor.setSelectedSensorPosition(0);

		motor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition, 0, 0);

		motor.setNeutralMode(NeutralMode.Brake);

		// turretOffsetPosition = motor.getSelectedSensorPosition(0);
		turretOffsetPosition = 0;
		turretCurrentPosition = 0;
		turretPastPosition = 0;

		periodic();
	}

	// @Override
	// disabled for at home

	public void periodic() { 
		turretCurrentPosition =	(int) limelightSubsystem.getYawFromTarget().rotations; //  motor.getSelectedSensorPosition(0);
	  
		turretPastPosition = turretCurrentPosition; 
		currentAngle = new Rotations((getMeasurement() == 0) ? 0 : (getMeasurement() / TICKS_PER_DEGREE), RotationUnits.DEGREE);
	}

	// Set the motor to 'output' unless it would cause the turret to
	// go outside the encoder max & min positions
	private void setMotorWithConstraint(double output) {
		final int position = motor.getSelectedSensorPosition();
		final double origOutput = output;

		if (output > 0) {
			// Positive values cause the encoder value to get smaller
			if (position < TURRET_MIN_POSITION) {
				output = 0;
			}
		} else if (output < 0) {
			// Negative values cause the encoder value to get larger
			if (position > TURRET_MAX_POSITION) {
				output = 0;
			}
		}

		//System.out.println("set: orig: " + origOutput + ", final: " + output + ", encoder: " + motor.getSelectedSensorPosition() + ", current: " + motor.getSupplyCurrent() + "," + motor.getStatorCurrent() + "," + motor.getOutputCurrent());

		motor.set(output);
	}

	public void set(double output) {
		output = MathUtils.constrain(output, -1, 1);

		if (output < 0.05 && output > -0.05) {
			output = 0;
		}

		setMotorWithConstraint(output);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		setMotorWithConstraint(output);
	}

	public double getCurrentDraw() {
		return motor.getStatorCurrent();
	}
}
