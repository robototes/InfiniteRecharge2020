package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.HoodConstants.MAX_EXTENSION;
import static frc.team2412.robot.subsystems.constants.HoodConstants.MAX_WITHDRAWL;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.Servo;
import frc.team2412.robot.commands.hood.HoodExtendCommand;
import frc.team2412.robot.commands.hood.HoodWithdrawCommand;

public class HoodSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	HoodSubsystem realHoodSubsystem;
	Servo mockedServo;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedServo = mock(Servo.class);
		realHoodSubsystem = new HoodSubsystem(mockedServo, mockedServo);

	}

	@Test
	public void HoodExtendCommandTest() {
		test(new HoodExtendCommand(realHoodSubsystem), mockedServo, MAX_EXTENSION);
	}
	
	@Test
	public void HoodWithdrawCommandTest() {
		test(new HoodWithdrawCommand(realHoodSubsystem), mockedServo, MAX_WITHDRAWL);
	}
}
