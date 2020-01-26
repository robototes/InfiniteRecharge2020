package frc.team2412.robot.subsystems;

import static org.junit.Assert.assertEquals;
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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.Commands.DriveCommands.DriveCommand;
import frc.team2412.robot.Commands.LiftCommands.LiftDownCommand;
import frc.team2412.robot.Commands.LiftCommands.LiftUpCommand;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.constants.LiftConstants.LiftState;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class DriveBaseSubsystemTest {

	// Mock instance of Example Subsystem
	DriveBaseSubsystem realDriveBaseSubsystem;
	DifferentialDrive mockedDifferentialDrive;
	ADXRS450_Gyro mockedGyro;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedDifferentialDrive = mock(DifferentialDrive.class);

		realDriveBaseSubsystem = new DriveBaseSubsystem(mockedDifferentialDrive, mockedGyro);
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	@Test
	public void DriveCommandOnDriveBaseSubsystemCallsDifferntialDriveSet() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedDifferentialDrive);

		// Create command
		DriveCommand driveCommand = new DriveCommand(realDriveBaseSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(driveCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that the solenoid was set correctly
		verify(mockedDifferentialDrive, times(1));
		assertEquals("Drive has been powered", realDriveBaseSubsystem.getCurrentRotation());

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

}
