package frc.team2412.robot.commands.turret;

import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_DEGREE;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_REVOLUTION;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TURRET_MAX_TOLERANCE;
import static frc.team2412.robot.subsystems.constants.TurretConstants.TURRET_PID_CONTROLLER;

import java.util.function.DoubleSupplier;

import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team2412.robot.subsystems.TurretSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class TurretRotateCommand extends PIDCommand {
	TurretSubsystem m_TurretSubsystem;

	protected double m_doubleSetpoint;

	public TurretRotateCommand(TurretSubsystem turretSubsystem, Rotations angleToRotate) {
		super(TURRET_PID_CONTROLLER, turretSubsystem::getMeasurement, 0, output -> turretSubsystem.set(-output),
				(Subsystem) turretSubsystem);

		configureSetpoint(TICKS_PER_DEGREE * angleToRotate.convertTo(RotationUnits.DEGREE));

		m_TurretSubsystem = turretSubsystem;

		getController().setTolerance(TURRET_MAX_TOLERANCE);
	}

	public void configureSetpoint(double inSetpoint) {
		m_doubleSetpoint = inSetpoint;

		if (m_doubleSetpoint > TICKS_PER_REVOLUTION / 2) {
			m_doubleSetpoint %= TICKS_PER_REVOLUTION;
		} else if (m_doubleSetpoint < -TICKS_PER_REVOLUTION / 2) {
			m_doubleSetpoint += TICKS_PER_REVOLUTION;
			m_doubleSetpoint %= TICKS_PER_REVOLUTION;
		}

		this.m_setpoint = new DoubleSupplier() {
			@Override
			public double getAsDouble() {
				return m_doubleSetpoint;
			}
		};
	}

	@Override
	public boolean isFinished() {
		System.out.printf("Position Error: %f \n", getController().getPositionError());
		return getController().atSetpoint();
	}

}
