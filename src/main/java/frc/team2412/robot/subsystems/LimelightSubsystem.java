package frc.team2412.robot.subsystems;

import java.util.Optional;

import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ShooterConstants;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterSkewDataPoint;

public class LimelightSubsystem extends SubsystemBase {

	// Store local values of distance and yaw so they aren't calculated multiple
	// times a loop
	public Optional<ShooterDistanceDataPoint> distanceData = Optional.empty();
	public Rotations yawFromTarget = new Rotations(0);
	public Optional<ShooterSkewDataPoint> skewData = Optional.empty();

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

		resetValues();

		getCameraStream();
	}

	private void resetValues() {
		distanceData = Optional.empty();
		yawFromTarget = new Rotations(0, RotationUnits.DEGREE);
		skewData = Optional.empty();
	}

	public void getCameraStream() {
		limeCam = new HttpCamera("limelight", "http://10.24.12.3:5801/stream.mjpg", HttpCameraKind.kMJPGStreamer);
	}

	//static int loopNum=0;
	public void getValues() {
		boolean valid = false;
		// If we have a target, set distance and yaw, otherwise error them
		if (limelight.hasValidTarget()) {
			valid = true;
			setDistanceDataFromTable();
			setYawFromTable();
			setSkewDataFromTable();
		} else {
			resetValues();
		}

		// loopNum++;
		// if (loopNum == 10) {
		// 	System.out.println("periodic: getVal: " + valid + ", " + yawFromTarget.convertTo(RotationUnits.DEGREE));
		// 	loopNum = 0;
		// }
	}

	public void setSkewDataFromTable() {
		skewData = Optional.ofNullable(ShooterConstants.getSkewDataPointFromTs(limelight.getTS()));
	}

	public Rotations getYawFromTarget() {
		return yawFromTarget;
	}

	public void setDistanceDataFromTable() {
		distanceData = Optional.of(ShooterConstants.getDistanceDataPointFromTy(limelight.getTY()));
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

	public Optional<ShooterDistanceDataPoint> getDistanceData() {
		return distanceData;
	}

	public Optional<ShooterSkewDataPoint> getSkewData() {
		return this.skewData;
	}

	public boolean innerGoalPossible() {
		return skewData.map(skew -> skew.innerGoalPossible).orElse(false);
	}
}
