package frc.team2412.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.math.MathUtils;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.TurretRotateCommand;

@SuppressWarnings("unused")
public class TurretSubsystem extends SubsystemBase {

	private Rotations m_currentAngle;
	private WPI_TalonSRX m_turretMotor;
	private LimelightSubsystem m_LimelightSubsystem;

	public TurretSubsystem(WPI_TalonSRX turretMotor, LimelightSubsystem limelightSubsystem) {
		this.m_turretMotor = turretMotor;
		m_currentAngle = new Rotations(turretMotor.getSelectedSensorPosition());
		m_LimelightSubsystem = limelightSubsystem;
		setDefaultCommand(new TurretRotateCommand(this, limelightSubsystem));
	}

	public void turnBasedOnLimelightAngle(Rotations limelightAngle) {
		m_turretMotor.set(ControlMode.PercentOutput,
				MathUtils.constrain(limelightAngle.convertTo(RotationUnits.DEGREE), -1, 1));
	}

	public Rotations getM_currentAngle() {
		return m_currentAngle;
	}

}
