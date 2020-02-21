package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.*;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class DriveBaseSubsystemTest {

	// Mock instance of Example Subsystem
	DriveBaseSubsystem realDriveBaseSubsystem;

	// This is called after tests, and makes sure that nothing is left open and
	// everything is ready for the next test class
	@After
	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		realDriveBaseSubsystem = mock(DriveBaseSubsystem.class);
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	
	@Ignore
	@Test
	public void DriveCommandOnDriveBaseSubsystemCallsMotorSet() {

		DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter),
				kDriveKinematics, 10);

		TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
				// Add kinematics to ensure max speed is actually obeyed
				.setKinematics(kDriveKinematics)
				// Apply the voltage constraint
				.addConstraint(autoVoltageConstraint);

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)),
				List.of(new Translation2d(5, 5)), new Pose2d(10, 0, new Rotation2d(0)), config);

		double time = exampleTrajectory.getTotalTimeSeconds();

		System.out.println(time);

		for (double i = 0; i <= time; i += time / 20) {
			System.out.println(exampleTrajectory.sample(i));
		}

	}

}
