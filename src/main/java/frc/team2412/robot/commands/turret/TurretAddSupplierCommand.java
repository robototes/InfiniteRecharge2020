package frc.team2412.robot.commands.turret;

import static frc.team2412.robot.subsystems.constants.TurretConstants.TICKS_PER_DEGREE;

import java.util.function.Supplier;

import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import frc.team2412.robot.subsystems.TurretSubsystem;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterSkewDataPoint;

public class TurretAddSupplierCommand extends TurretAddRotateCommand {

	Supplier<ShooterSkewDataPoint> angleToAdd;

	public TurretAddSupplierCommand(TurretSubsystem turretSubsystem,
			Supplier<ShooterSkewDataPoint> angleToAddToRotate) {
		super(turretSubsystem, new Rotations(0));

		angleToAdd = angleToAddToRotate;
	}

	@Override
	public void initialize() {
		super.initialize();
		Rotations currentAngle = new Rotations(m_TurretSubsystem.getCurrentAngle().getValue());
		configureSetpoint(TICKS_PER_DEGREE
				* currentAngle.add(new Rotations(angleToAdd.get().m_turretDeltaForInner.value(), RotationUnits.DEGREE))
						.convertTo(RotationUnits.DEGREE));
	}

}
