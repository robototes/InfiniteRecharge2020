package frc.team2412.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;
import com.robototes.sensors.Limelight;
import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import frc.team2412.robot.subsystems.constants.LimelightConstants;;

public class LimelightSubsystemTest {

	// Instances for multiple tests to save time coding
	Limelight mockedLimelight;
	LimelightSubsystem realLimelightSubsystem;

	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		// Mock the limelight and give it to realLimelightSubsystem
		mockedLimelight = mock(Limelight.class);
		realLimelightSubsystem = new LimelightSubsystem(mockedLimelight);
	}

	@Ignore
	@Test
	public void LimelightSubsystemSetsCorrectDistanceAndYawWhenTargetIsFound() {

		// Values for the "tx" and "ty" values of the limelight
		double tx = 15;
		double ty = 45;

		// Configure mocked limelight to find a valid target and to return the correct
		// values to realLimelightSubsystem
		when(mockedLimelight.hasValidTarget()).thenReturn(true);
		when(mockedLimelight.getTX()).thenReturn(tx);
		when(mockedLimelight.getTY()).thenReturn(ty);

		// Register realLimelightSubsystem and run it once
		realLimelightSubsystem.getValues();

		// Assert that the passed in rotation value matches a confirmed value
		assertEquals("Limelight has correct yaw", new Rotations(tx, RotationUnits.DEGREE),
				realLimelightSubsystem.getYawFromTarget());

		// Get the "distance" to the fake target manually
		double distance = (LimelightConstants.TARGET_CENTER_HEIGHT.subtract(LimelightConstants.LIFT_UP_HEIGHT)
				.getValue())
				/ (Math.tan(new Rotations(ty, RotationUnits.DEGREE).add(LimelightConstants.LIMELIGHT_MOUNT_ANGLE)
						.convertTo(RotationUnits.RADIAN)));

		// Assert that manual and calculated distances are equal
		assertEquals("Limelight has correct distance", new Distance(distance), new Distance(distance));

		TestWithScheduler.schedulerClear();
	}

}