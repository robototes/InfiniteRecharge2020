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
import frc.team2412.robot.commands.turret.TurretFollowLimelightCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class TurretSubsystem extends PIDSubsystem implements Loggable {

	@Log.ToString(tabName = "Turret")
	private Rotations m_currentAngle;
	@Log(tabName = "Turret")
	private WPI_TalonSRX m_turretMotor;

	private LimelightSubsystem m_limelightSubsystem;
	private int m_turretOffsetPosition = 0;
	private int m_turretPastPosition;
	private int m_turretCurrentPosition;

	public TurretSubsystem(WPI_TalonSRX turretMotor, LimelightSubsystem limelightSubsystem) {
		super(TURRET_PID_CONTROLLER);
		this.m_turretMotor = turretMotor;
		m_limelightSubsystem = limelightSubsystem;

		initTurretEncoder();

		setDefaultCommand(new TurretFollowLimelightCommand(this, m_limelightSubsystem));
	}

	public Rotations getCurrentAngle() {
		return m_currentAngle;
	}

	@Override
	public double getMeasurement() {
		return m_turretCurrentPosition - m_turretOffsetPosition;
	}

	public void initTurretEncoder() {
		m_turretMotor.configFactoryDefault();
		m_turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
		m_turretMotor.setSelectedSensorPosition(0);

		m_turretMotor.setNeutralMode(NeutralMode.Brake);

		m_turretOffsetPosition = m_turretMotor.getSelectedSensorPosition(0);
		m_turretCurrentPosition = 0;
		m_turretPastPosition = 0;

		periodic();
	}

	@Override
	public void periodic() {
		m_turretCurrentPosition = m_turretMotor.getSelectedSensorPosition(0);

		if (m_turretCurrentPosition - m_turretPastPosition > ENCODER_MAX_ERROR_JUMP) {
			m_turretOffsetPosition += TICKS_PER_REVOLUTION;

		} else if (Math.abs(m_turretCurrentPosition - m_turretPastPosition) > ENCODER_MAX_ERROR_JUMP) {
			m_turretCurrentPosition = m_turretPastPosition;
		}

		m_turretPastPosition = m_turretCurrentPosition;
		m_currentAngle = new Rotations((getMeasurement() == 0) ? 0 : (getMeasurement() / TICKS_PER_DEGREE),
				RotationUnits.DEGREE);
		System.out.println(getMeasurement());
	}

	@Config
	public void set(double output) {
		output = MathUtils.constrain(output, -1, 1);

		if (output < 0.05 && output > -0.05) {
			output = 0;
		}

		m_turretMotor.set(output);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		m_turretMotor.set(output);
	}

	public double getCurrentDraw() {
		return m_turretMotor.getStatorCurrent();
	}
}
