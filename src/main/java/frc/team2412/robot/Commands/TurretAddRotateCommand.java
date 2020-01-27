package frc.team2412.robot.Commands;

import static frc.team2412.robot.Subsystems.constants.TurretConstants.TICKS_PER_DEGREE;

import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class TurretAddRotateCommand extends TurretRotateCommand {

	public Rotations m_rotationsToAdd;

	public TurretAddRotateCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem,
			Rotations angleToAddToRotate) {
		super(turretSubsystem, limelightSubsystem, turretSubsystem.getCurrentAngle().add(angleToAddToRotate));

		m_rotationsToAdd = angleToAddToRotate;
	}

	@Override
	public void initialize() {
		super.initialize();
		Rotations currentAngle = new Rotations(m_TurretSubsystem.getCurrentAngle().getValue());
		configureSetpoint(TICKS_PER_DEGREE * currentAngle.add(m_rotationsToAdd).convertTo(RotationUnits.DEGREE));
	}
}
