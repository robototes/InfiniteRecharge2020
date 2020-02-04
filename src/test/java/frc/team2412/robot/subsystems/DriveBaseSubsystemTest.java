package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.Commands.DriveCommands.DriveCommand;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class DriveBaseSubsystemTest {

	// Mock instance of Example Subsystem
	DriveBaseSubsystem realDriveBaseSubsystem;
	DifferentialDrive mockedDifferntialDrive;
	ADXRS450_Gyro mockedGyro;
	GenericHID mockedGenericHID;
	Joystick mockedJoystick;

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

		mockedDifferntialDrive = mock(DifferentialDrive.class);
		mockedGyro = mock(ADXRS450_Gyro.class);
		mockedJoystick = mock(Joystick.class);
		mockedGenericHID = mock(GenericHID.class);

		realDriveBaseSubsystem = new DriveBaseSubsystem(mockedDifferntialDrive, mockedGyro, mockedJoystick);
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	@Test
	@Ignore // TODO: Fix the null pointers this test produces
	public void DriveCommandOnDriveBaseSubsystemCallsMotorSet() {
		// Reset the mocked objects to make sure all mock values are reset
		reset(mockedGyro);
		reset(mockedJoystick);

		// Create command
		DriveCommand driveCommand = new DriveCommand(realDriveBaseSubsystem, mockedJoystick);

		when(mockedGyro.getAngle()).thenReturn(0.5);
		System.out.println(mockedGyro.getAngle());

		when(mockedJoystick.getY()).thenReturn(1.0);
		System.out.println(mockedJoystick.getY());

		when(mockedJoystick.getTwist()).thenReturn(0.0);
		System.out.println(mockedJoystick.getTwist());

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(driveCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedDifferntialDrive, times(1)).arcadeDrive(1.0, 0, true);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

}
