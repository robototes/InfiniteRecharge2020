package frc.team2412.robot.Subsystems;

import static frc.team2412.robot.Subsystems.constants.TurretConstants.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.math.MathUtils;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.team2412.robot.Commands.turret.TurretFollowLimelightCommand;

public class TurretSubsystem extends PIDSubsystem {

	private Rotations m_currentAngle;
	private WPI_TalonSRX m_turretMotor;
	private LimelightSubsystem m_LimelightSubsystem;
	private int m_TurretOffsetPosition = 0;
	private int m_TurretPastPosition;
	private int m_TurretCurrentPosition;

	public TurretSubsystem(WPI_TalonSRX turretMotor, LimelightSubsystem limelightSubsystem) {
		super(TURRET_PID_CONTROLLER);
		this.m_turretMotor = turretMotor;
		m_LimelightSubsystem = limelightSubsystem;

		initTurretEncoder();

		setDefaultCommand(new TurretFollowLimelightCommand(this, m_LimelightSubsystem));
	}

	public void initTurretEncoder() {
		m_turretMotor.configFactoryDefault();
		m_turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
		m_turretMotor.setSelectedSensorPosition(0);

		m_turretMotor.setNeutralMode(NeutralMode.Brake);

		m_TurretOffsetPosition = m_turretMotor.getSelectedSensorPosition(0);
		m_TurretCurrentPosition = 0;
		m_TurretPastPosition = 0;

		periodic();
	}

	@Override
	public void periodic() {
		m_TurretCurrentPosition = m_turretMotor.getSelectedSensorPosition(0);

		if (m_TurretCurrentPosition - m_TurretPastPosition > 3800) {
			m_TurretOffsetPosition += TICKS_PER_REVOLUTION;

		} else if (Math.abs(m_TurretCurrentPosition - m_TurretPastPosition) > 500) {
			m_TurretCurrentPosition = m_TurretPastPosition;
		}

		m_TurretPastPosition = m_TurretCurrentPosition;
		m_currentAngle = new Rotations((getMeasurement() == 0) ? 0 : (getMeasurement() / TICKS_PER_DEGREE),
				RotationUnits.DEGREE);
		System.out.println(getMeasurement());
	}

	public Rotations getCurrentAngle() {
		return m_currentAngle;
	}

	@Override
	public void useOutput(double output, double setpoint) {
		m_turretMotor.set(output);
	}

	@Override
	public double getMeasurement() {
		return m_TurretCurrentPosition - m_TurretOffsetPosition;
	}

	public void set(double output) {
		output = MathUtils.constrain(output, -1, 1);

		if (output < 0.05 && output > -0.05) {
			output = 0;
		}

		m_turretMotor.set(output);
	}
}
