package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revrobotics.CANSparkMax;
import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.Commands.IntakeCommands.IntakeOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeOnCommand;
import frc.team2412.robot.Subsystems.IntakeMotorOnOffSubsystem;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeMotorOnOffSubsystemTest {

	// Mock instance of Example Subsystem
	IntakeMotorOnOffSubsystem realIntakeMotorOnOffSubsystem;
	CANSparkMax mockedIntakeMotor;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedIntakeMotor = mock(CANSparkMax.class);

		realIntakeMotorOnOffSubsystem = new IntakeMotorOnOffSubsystem(mockedIntakeMotor);
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	@Test
	public void IntakeUpCommandOnIntakeUpDownSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeMotor);

		// Create command
		IntakeOnCommand IntakeOnCommand = new IntakeOnCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(IntakeOnCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeMotor, times(1)).set(1);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeOffCommandOnIntakeSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeMotor);

		// Create command
		IntakeOffCommand IntakeOffCommand = new IntakeOffCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(IntakeOffCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeMotor, times(1)).set(0);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	// This is called after tests, and makes sure that nothing is left open and
	// everything is ready for the next test class
	@After
	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

}
