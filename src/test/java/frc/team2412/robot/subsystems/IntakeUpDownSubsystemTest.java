package frc.team2412.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.subsystems.constants.IntakeConstants.IntakeState;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeUpDownSubsystemTest {

	// Mock instance of Example Subsystem
	IntakeLiftSubsystem realIntakeUpDownSubsystem;
	Solenoid mockedLiftSolenoid;
	Solenoid mockedLiftSolenoid2;

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

		mockedLiftSolenoid = mock(Solenoid.class);
		mockedLiftSolenoid2 = mock(Solenoid.class);

		realIntakeUpDownSubsystem = new IntakeLiftSubsystem(mockedLiftSolenoid, mockedLiftSolenoid2);
	}

	@Ignore
	@Test
	public void IntakeDownCommandOnIntakeSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedLiftSolenoid);

		// Create command
		IntakeBackDownCommand intakeBackDownCommand = new IntakeBackDownCommand(realIntakeUpDownSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeBackDownCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedLiftSolenoid, times(1)).set(IntakeState.EXTENDED.value);
		assertEquals("Lift has the correct state", realIntakeUpDownSubsystem.getCurrentState(), IntakeState.EXTENDED);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	@Ignore
	@Test
	public void IntakeUpCommandOnIntakeUpDownSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedLiftSolenoid);

		// Create command
		IntakeBackUpCommand IntakeBackUpCommand = new IntakeBackUpCommand(realIntakeUpDownSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(IntakeBackUpCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedLiftSolenoid, times(1)).set(IntakeState.WITHDRAWN.value);
		assertEquals("Lift has the correct state", realIntakeUpDownSubsystem.getCurrentState(), IntakeState.WITHDRAWN);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

}
