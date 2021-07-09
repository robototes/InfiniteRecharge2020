package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.TurretConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.*;
import com.robototes.math.MathUtils;

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
//		motor.configFactoryDefault();
//		motor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
//		motor.setSelectedSensorPosition(0);

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
