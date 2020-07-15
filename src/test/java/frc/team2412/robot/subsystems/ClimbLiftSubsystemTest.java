package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2412.robot.commands.climb.ClimbLiftDownCommand;
import frc.team2412.robot.commands.climb.ClimbLiftUpCommand;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class ClimbLiftSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	ClimbLiftSubsystem realClimbLiftSubsystem;
	Solenoid mockedLiftSolenoid;
	Solenoid mockedLiftSolenoid2;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test

	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedLiftSolenoid = mock(Solenoid.class);
		mockedLiftSolenoid2 = mock(Solenoid.class);

		realClimbLiftSubsystem = new ClimbLiftSubsystem(mockedLiftSolenoid, mockedLiftSolenoid2);
	}

	@Test
	public void ClimbLiftUpCommandTest() {
		test(new ClimbLiftUpCommand(realClimbLiftSubsystem), mockedLiftSolenoid, true);
	}

	@Test
	public void ClimbLiftDownCommandTest() {
		test(new ClimbLiftDownCommand(realClimbLiftSubsystem), mockedLiftSolenoid, false);
	}

}
