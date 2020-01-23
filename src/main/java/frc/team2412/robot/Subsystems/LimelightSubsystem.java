package frc.team2412.robot.Subsystems;

import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.LimelightReadCommand;
import frc.team2412.robot.Subsystems.constants.LimelightConstants;

public class LimelightSubsystem extends SubsystemBase {

	// Store local values of distance and yaw so they aren't calculated multiple
	// times a loop
	public Distance m_distanceToTarget;
	public Rotations m_yawFromTarget;

	// Store the limelight
	private Limelight m_limelight;

	private int loopCount = 0;

	public LimelightSubsystem(Limelight limelight) {
		this.m_limelight = limelight;

		m_limelight.setLedMode(LEDMode.OFF);
		m_limelight.setCamMode(CamMode.VISION_PROCESSER);
		m_limelight.setPipeline(Pipeline.FOUR);
		m_limelight.setSnapshotMode(SnapshotMode.OFF);
		m_limelight.setStreamMode(StreamMode.STANDARD);

		this.m_distanceToTarget = new Distance(0);
		this.m_yawFromTarget = new Rotations(0);

		this.setDefaultCommand(new LimelightReadCommand(this));
	}

	@Override
	public void periodic() {

	}

	public void getValues() {
		if (++loopCount % 60 < 15) {

			System.out.println("getting values!");
			// If we have a target, set distance and yaw, otherwise error them
			m_limelight.setLedMode(LEDMode.ON);
			if (m_limelight.hasValidTarget()) {
				setDistanceFromTable();
				setYawFromTable();
			}
//			else {
//			setValuesToError();
//			}
		} else {
			m_limelight.setLedMode(LEDMode.OFF);
		}
	}

	public void setValuesToError() {
		// Error distance and yaw to make sure that we know if we dont have a target
		m_distanceToTarget = new Distance(Double.NaN);
		m_yawFromTarget = new Rotations(Double.NaN);
	}

	public void setYawFromTable() {
		// Set the yaw to a degree value from the limelight
		m_yawFromTarget = new Rotations(m_limelight.getTX(), RotationUnits.DEGREE);
	}

	public void setDistanceFromTable() {
		// Formula from docs.limelight.io:
		// d = (h2-h1) / tan(a1+a2)

		// Find h2-h1, or delta y (opposite side)
		Distance targetHeightMinusLimelightHeight = LimelightConstants.TARGET_CENTER_HEIGHT
				.subtract(LimelightConstants.LIFT_UP_HEIGHT);

		// Get the angle to the target from the limelight and add that to the mount
		// angle to get the angle from the horizontal
		Rotations angleUpDownToTarget = new Rotations(m_limelight.getTY(), RotationUnits.DEGREE);
		Rotations angleFromHorizontal = angleUpDownToTarget.add(LimelightConstants.LIMELIGHT_MOUNT_ANGLE);

		// Get the tangent of the angle (opposite/adjacent)
		double tangentOfAngle = Math.tan(angleFromHorizontal.getValue());

		// Divide delta y by the tangent to get the distance (adjacent side)
		m_distanceToTarget = targetHeightMinusLimelightHeight.divide(new Distance(tangentOfAngle));
	}

	public Distance getDistanceToTarget() {
		return m_distanceToTarget;
	}

	public Rotations getYawFromTarget() {
		return m_yawFromTarget;
	}

	public void stopLimelight() {
		m_limelight.setLedMode(LEDMode.OFF);
	}
}
