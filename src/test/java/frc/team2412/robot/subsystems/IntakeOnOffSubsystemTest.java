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
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffIntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnIntakeBackOffCommand;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeOnOffSubsystemTest {

	// Mock instance of Example Subsystem
	IntakeOnOffSubsystem realIntakeMotorOnOffSubsystem;
	CANSparkMax mockedIntakeFrontMotor;
	CANSparkMax mockedIntakeBackMotor;

	// This is called after tests, and makes sure that nothing is left open and
	// everything is ready for the next test class
	@After
	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedIntakeFrontMotor = mock(CANSparkMax.class);
		mockedIntakeBackMotor = mock(CANSparkMax.class);

		realIntakeMotorOnOffSubsystem = new IntakeOnOffSubsystem(mockedIntakeFrontMotor, mockedIntakeBackMotor);
	}

	@Test
	public void IntakeBackOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeBackOffCommand intakeBackOffCommand = new IntakeBackOffCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeBackOffCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeBackMotor, times(1)).set(0);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeBackOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeBackOnCommand intakeBackOnCommand = new IntakeBackOnCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeBackOnCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeBackMotor, times(1)).set(1);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeFrontOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeFrontOffCommand intakeFrontOffCommand = new IntakeFrontOffCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeFrontOffCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeFrontMotor, times(1)).set(0);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeFrontOffIntakeBackOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeFrontOffIntakeBackOnCommand intakeFrontOffIntakeBackOnCommand = new IntakeFrontOffIntakeBackOnCommand(
				realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeFrontOffIntakeBackOnCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeFrontMotor, times(1)).set(0);
		verify(mockedIntakeBackMotor, times(1)).set(1);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeFrontOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeFrontOnCommand intakeFrontOnCommand = new IntakeFrontOnCommand(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeFrontOnCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeFrontMotor, times(1)).set(1);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeFrontOnIntakeBackOffIntakeMotorOnOffSubsystemCallsMotorSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);

		// Create command
		IntakeFrontOnIntakeBackOffCommand intakeFrontOnIntakeBackOffCommand = new IntakeFrontOnIntakeBackOffCommand(
				realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeFrontOnIntakeBackOffCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedIntakeFrontMotor, times(1)).set(1);
		verify(mockedIntakeBackMotor, times(1)).set(0);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

}
