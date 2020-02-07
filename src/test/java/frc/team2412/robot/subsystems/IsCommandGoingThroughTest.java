package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.Commands.hood.HoodExtendCommand;
import frc.team2412.robot.Subsystems.HoodSubsystem;

public class IsCommandGoingThroughTest {
	
	
	// Mock instance of Example Subsystem
		HoodSubsystem realHoodSubsystem;
		Servo mockedServo;
		
		
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
			
			mockedServo = mock(Servo.class);
			realHoodSubsystem = new HoodSubsystem(mockedServo);
			
		}
		
		@Test
		public void ExampleCommandOnExampleSubsystem() {
			// Reset the subsystem to make sure all mock values are reset
			reset(mockedServo);

			// Create command
			HoodExtendCommand testHoodCommand = new HoodExtendCommand(realHoodSubsystem);

			// Create a fake button that will be "pressed"
			MockButton fakeButton = new MockButton();

			// Tell the button to run example command when pressed
			fakeButton.whenPressed(testHoodCommand);

			// Push the button and run the scheduler once
			fakeButton.push();
			CommandScheduler.getInstance().run();
			fakeButton.release();

			// Verify that subsystemMethod was called once
			verify(mockedServo, times(1)).set(1);

			// Clear the scheduler
			TestWithScheduler.schedulerClear();
		}
}
