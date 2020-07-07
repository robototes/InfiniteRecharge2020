package com.robototes.helpers;

import static org.mockito.Mockito.verify;

import org.junit.After;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class TestBase {

	public void test(Command command, SpeedController changedObject, double wantedValue) {
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(command);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		verify(changedObject).set(wantedValue);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	public void test(Command command, Solenoid changedObject, boolean wantedValue) {
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(command);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		verify(changedObject).set(wantedValue);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	public void test(Command command, DoubleSolenoid changedObject, Value wantedValue) {
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(command);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		verify(changedObject).set(wantedValue);

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
