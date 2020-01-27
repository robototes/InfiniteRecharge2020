package frc.team2412.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;
import com.robototes.math.MathUtils;
import com.robototes.sensors.Limelight;
import com.robototes.units.Distance;
import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.constants.LimelightConstants;;

public class LimelightSubsystemTest {

	// Instances for multiple tests to save time coding
	Limelight mockedLimelight;
	LimelightSubsystem realLimelightSubsystem;

	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		// Mock the limelight and give it to realLimelightSubsystem
		mockedLimelight = mock(Limelight.class);
		realLimelightSubsystem = new LimelightSubsystem(mockedLimelight);
	}

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
		CommandScheduler.getInstance().registerSubsystem(realLimelightSubsystem);
		CommandScheduler.getInstance().run();

		for (int i = 0; i < 60; i++) {
			realLimelightSubsystem.getValues();
		}

		// Assert that the passed in rotation value matches a confirmed value
		assertEquals("Limelight has correct yaw", new Rotations(tx, RotationUnits.DEGREE),
				realLimelightSubsystem.getYawFromTarget());

		// Get the "distance" to the fake target manually
		double distance = (LimelightConstants.TARGET_CENTER_HEIGHT.subtract(LimelightConstants.LIFT_UP_HEIGHT)
				.getValue())
				/ (Math.tan(new Rotations(ty, RotationUnits.DEGREE).add(LimelightConstants.LIMELIGHT_MOUNT_ANGLE)
						.getValue()));

		// Assert that manual and calculated distances are equal
		assertEquals("Limelight has correct distance", new Distance(distance),
				realLimelightSubsystem.getDistanceToTarget());

		TestWithScheduler.schedulerClear();
	}

	/*
	 * @Test public void LimelightSubsystemSetsErrorValuesWhenNoTargetIsFound() {
	 * 
	 * // Configure the limelight to not find a target
	 * when(mockedLimelight.hasValidTarget()).thenReturn(false);
	 * 
	 * // Register realLimelightSubsystem and run it once
	 * CommandScheduler.getInstance().registerSubsystem(realLimelightSubsystem);
	 * CommandScheduler.getInstance().run();
	 * 
	 * // Make sure that both yaw and distance have Double.NaN values
	 * assertEquals("Limelight has NaN yaw", Double.NaN,
	 * realLimelightSubsystem.getYawFromTarget().getValue(), MathUtils.EPSILON);
	 * 
	 * assertEquals("Limelight has NaN distance", Double.NaN,
	 * realLimelightSubsystem.getDistanceToTarget().getValue(), MathUtils.EPSILON);
	 * 
	 * TestWithScheduler.schedulerClear(); }
	 */

	@After
	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

}
