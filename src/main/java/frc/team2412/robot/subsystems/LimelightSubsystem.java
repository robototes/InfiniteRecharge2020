package frc.team2412.robot.subsystems;

import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;
import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.commands.limelight.LimelightReadCommand;
import frc.team2412.robot.subsystems.constants.LimelightConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class LimelightSubsystem extends SubsystemBase implements Loggable {

	// Store local values of distance and yaw so they aren't calculated multiple
	// times a loop
	@Log.ToString(tabName = "Turret")
	public Distance m_distanceToTarget;
	@Log.ToString(tabName = "turret")
	public Rotations m_yawFromTarget;

	@Log.CameraStream
	public HttpCamera limeCam;

	// Store the limelight
	private Limelight m_limelight;

	public LimelightSubsystem(Limelight limelight) {
		this.m_limelight = limelight;
		m_limelight.setLedMode(LEDMode.ON);
		m_limelight.setCamMode(CamMode.VISION_PROCESSER);
		m_limelight.setPipeline(Pipeline.FOUR);
		m_limelight.setSnapshotMode(SnapshotMode.OFF);
		m_limelight.setStreamMode(StreamMode.STANDARD);

		this.m_distanceToTarget = new Distance(0);
		this.m_yawFromTarget = new Rotations(0);

		this.setDefaultCommand(new LimelightReadCommand(this));

	}

	public void getCameraStream() {
		HttpCamera limelightFeed = new HttpCamera("limelight", "http://10.24.12.11:5801/stream.mjpg",
				HttpCameraKind.kMJPGStreamer);

		limeCam = limelightFeed;
	}

	public void accurateAim() {
		if (m_limelight.hasValidTarget()) {
			setDistanceFromTable();
			setYawFromTable();

			// Complex equation that wont be explained here because it is really confusing
			double skewOfTarget = m_limelight.getNetworkTable().getEntry("ts").getDouble(0);

			double cosOfSkew = Math
					.cos(new Rotations(180 - skewOfTarget, RotationUnits.DEGREE).convertTo(RotationUnits.RADIAN));

			double angleFromYawToInner = Math.acos(
					(m_distanceToTarget.getValue() - LimelightConstants.INNER_TARGET_DISTANCE.getValue() * cosOfSkew)
							/ Math.sqrt(Math.pow(m_distanceToTarget.getValue(), 2)
									+ Math.pow(LimelightConstants.INNER_TARGET_DISTANCE.getValue(), 2)
									- 2 * m_distanceToTarget.getValue()
											* LimelightConstants.INNER_TARGET_DISTANCE.getValue() * cosOfSkew));

			m_yawFromTarget = m_yawFromTarget.add(new Rotations(angleFromYawToInner, RotationUnits.RADIAN));
		}
	}

	public Distance getDistanceToTarget() {
		return m_distanceToTarget;
	}

	public void getValues() {
		// If we have a target, set distance and yaw, otherwise error them
		if (m_limelight.hasValidTarget()) {
			setDistanceFromTable();
			setYawFromTable();
		}
	}

	public Rotations getYawFromTarget() {
		return m_yawFromTarget;
	}

	@Override
	public void periodic() {

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
		double tangentOfAngle = Math.tan(angleFromHorizontal.convertTo(RotationUnits.RADIAN));

		// Divide delta y by the tangent to get the distance (adjacent side)
		m_distanceToTarget = targetHeightMinusLimelightHeight.divide(new Distance(tangentOfAngle));
	}

	public void setYawFromTable() {
		// Set the yaw to a degree value from the limelight
		Rotations yawFromOuterTarget = new Rotations(m_limelight.getTX(), RotationUnits.DEGREE);

		m_yawFromTarget = yawFromOuterTarget;
	}

	public void stopLimelight() {
		m_limelight.setLedMode(LEDMode.OFF);
	}
}
