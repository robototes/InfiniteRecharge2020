package frc.team2412.robot.Subsystems;

import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;
import com.robototes.sensors.Limelight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.LimelightConstants;

public class LimelightSubsystem extends SubsystemBase {

	public Distance m_distanceToTarget;
	public Rotations m_yawFromTarget;

	private Limelight m_limelight;

	public LimelightSubsystem(Limelight limelight) {
		this.m_limelight = limelight;
		this.m_distanceToTarget = new Distance(0);
		this.m_yawFromTarget = new Rotations(0);
	}

	@Override
	public void periodic() {
		if (m_limelight.hasValidTarget()) {
			setDistanceFromTable();
			setYawFromTable();
		} else {
			setValuesToError();
		}
	}

	public void setValuesToError() {
		m_distanceToTarget = new Distance(Double.NaN);
		m_yawFromTarget = new Rotations(Double.NaN);
	}

	public void setYawFromTable() {
		m_yawFromTarget = new Rotations(m_limelight.getTX(), RotationUnits.DEGREE);
	}

	public void setDistanceFromTable() {
		// Formula from docs.limelight.io:
		// d = (h2-h1) / tan(a1+a2)

		Distance targetHeightMinusLimelightHeight = LimelightConstants.TARGET_CENTER_HEIGHT
				.subtract(LimelightConstants.LIFT_UP_HEIGHT);

		Rotations angleUpDownToTarget = new Rotations(m_limelight.getTY(), RotationUnits.DEGREE);
		Rotations angleFromHorizontal = angleUpDownToTarget.add(LimelightConstants.LIMELIGHT_MOUNT_ANGLE);

		double tangentOfAngle = Math.tan(angleFromHorizontal.getValue());

		m_distanceToTarget = targetHeightMinusLimelightHeight.divide(new Distance(tangentOfAngle));
	}

	public Distance getDistanceToTarget() {
		return m_distanceToTarget;
	}

	public Rotations getYawFromTarget() {
		return m_yawFromTarget;
	}
}
