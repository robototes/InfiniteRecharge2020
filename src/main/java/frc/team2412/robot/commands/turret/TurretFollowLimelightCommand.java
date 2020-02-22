package frc.team2412.robot.commands.turret;

import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_DEGREE;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_REVOLUTION;

import java.util.function.DoubleSupplier;

import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import frc.team2412.robot.subsystems.LimelightSubsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class TurretFollowLimelightCommand extends TurretAddRotateCommand {

	private LimelightSubsystem m_LimelightSubsystem;

	public TurretFollowLimelightCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem) {
		super(turretSubsystem, new Rotations(0));

		this.m_LimelightSubsystem = limelightSubsystem;
	}

	@Override
	public void initialize() {
		super.initialize();

		this.m_setpoint = new DoubleSupplier() {
			@Override
			public double getAsDouble() {
				Rotations currentAngle = new Rotations(m_TurretSubsystem.getCurrentAngle().getValue());

				m_doubleSetpoint = TICKS_PER_DEGREE
						* currentAngle.add(m_LimelightSubsystem.getYawFromTarget()).convertTo(RotationUnits.DEGREE);

				if (m_doubleSetpoint > TICKS_PER_REVOLUTION / 2) {
					m_doubleSetpoint %= TICKS_PER_REVOLUTION;
				} else if (m_doubleSetpoint < -TICKS_PER_REVOLUTION / 2) {
					m_doubleSetpoint += TICKS_PER_REVOLUTION;
					m_doubleSetpoint %= TICKS_PER_REVOLUTION;
				}

				return m_doubleSetpoint;
			}
		};
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
