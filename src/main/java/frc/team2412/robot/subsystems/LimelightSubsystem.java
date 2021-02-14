package frc.team2412.robot.subsystems;

import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ShooterConstants;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterSkewDataPoint;

public class LimelightSubsystem extends SubsystemBase {

	// Store local values of distance and yaw so they aren't calculated multiple
	// times a loop
	public ShooterDistanceDataPoint distanceData;
	public Rotations yawFromTarget;
	public ShooterSkewDataPoint skewData;

	public HttpCamera limeCam;

	// Store the limelight
	private Limelight limelight;

	public LimelightSubsystem(Limelight limelight) {
		this.limelight = limelight;
		this.limelight.setLedMode(LEDMode.ON);
		this.limelight.setCamMode(CamMode.VISION_PROCESSER);
		this.limelight.setPipeline(Pipeline.FOUR);
		this.limelight.setSnapshotMode(SnapshotMode.OFF);
		this.limelight.setStreamMode(StreamMode.STANDARD);

		yawFromTarget = new Rotations(0);

		getCameraStream();
	}

	public void getCameraStream() {
		limeCam = new HttpCamera("limelight", "http://10.24.12.3:5801/stream.mjpg", HttpCameraKind.kMJPGStreamer);
	}

	public void getValues() {
		// If we have a target, set distance and yaw, otherwise error them
		if (limelight.hasValidTarget()) {
			setDistanceDataFromTable();
			setYawFromTable();
			setSkewDataFromTable();
		}
	}

	public void setSkewDataFromTable() {
		skewData = ShooterConstants.getSkewDataPointFromTs(limelight.getTS());
	}

	public Rotations getYawFromTarget() {
		return yawFromTarget;
	}

	public void setDistanceDataFromTable() {
		distanceData = ShooterConstants.getDistanceDataPointFromTy(limelight.getTY());
	}

	public void setYawFromTable() {
		yawFromTarget = new Rotations(limelight.getTX(), RotationUnits.DEGREE);
	}

	public void stopLimelight() {
		limelight.setLedMode(LEDMode.OFF);
	}

	public void startLimelight() {
		limelight.setLedMode(LEDMode.ON);
	}

	public ShooterDistanceDataPoint getDistanceData() {
		return distanceData;
	}

	public ShooterSkewDataPoint getSkewData() {
		return this.skewData;
	}

	public boolean innerGoalPossible() {
		return skewData.innerGoalPossible;
	}
}
