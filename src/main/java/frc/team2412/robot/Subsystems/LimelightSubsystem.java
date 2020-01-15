package frc.team2412.robot.Subsystems;

import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;
import com.robototes.sensors.Limelight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.LimelightConstants;

public class LimelightSubsystem extends SubsystemBase {

	public Distance distanceToTarget;
	public Rotations yawFromTarget;

	private Limelight limelight;

	public LimelightSubsystem(Limelight limelight) {
		this.limelight = limelight;
		this.distanceToTarget = new Distance(0);
		this.yawFromTarget = new Rotations(0);
	}

	@Override
	public void periodic() {
		if (getValidTarget()) {
			setDistanceFromTable();
			setYawFromTable();
		} else {
			setValuesToError();
		}
	}

	public void setValuesToError() {
		distanceToTarget = new Distance(Double.NaN);
		yawFromTarget = new Rotations(Double.NaN);
	}

	public boolean getValidTarget() {
		return limelight.hasValidTarget();
	}

	public void setYawFromTable() {
		yawFromTarget = new Rotations(limelight.getTX(), RotationUnits.DEGREE);
	}

	public void setDistanceFromTable() {
		// d = (h2-h1) / tan(a1+a2)

		Distance targetHeightMinusLimelightHeight = LimelightConstants.TARGET_CENTER_HEIGHT
				.subtract(LimelightConstants.LIFT_UP_HEIGHT);

		Rotations angleUpDownToTarget = new Rotations(limelight.getTY(), RotationUnits.DEGREE);
		Rotations angleFromHorizontal = angleUpDownToTarget.add(LimelightConstants.LIMELIGHT_MOUNT_ANGLE);

		double tangentOfAngle = Math.tan(angleFromHorizontal.getValue());

		distanceToTarget = targetHeightMinusLimelightHeight.divide(new Distance(tangentOfAngle));
	}

	public Distance getDistanceToTarget() {
		return distanceToTarget;
	}

	public Rotations getYawFromTarget() {
		return yawFromTarget;
	}
}
