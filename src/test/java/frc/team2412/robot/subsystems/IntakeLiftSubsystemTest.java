package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontUpCommand;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeLiftSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	IntakeLiftSubsystem realIntakeUpDownSubsystem;
	Solenoid mockedFrontSolenoid;
	Solenoid mockedBackSolenoid;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedFrontSolenoid = mock(Solenoid.class);
		mockedBackSolenoid = mock(Solenoid.class);

		realIntakeUpDownSubsystem = new IntakeLiftSubsystem(mockedFrontSolenoid, mockedBackSolenoid);
	}

	@Test
	public void IntakeBackDownCommandTest() {
		test(new IntakeBackDownCommand(realIntakeUpDownSubsystem), mockedBackSolenoid, true);
	}

	@Test
	public void IntakeBackUpCommandTest() {
		test(new IntakeBackUpCommand(realIntakeUpDownSubsystem), mockedBackSolenoid, false);
	}
	
	@Test
	public void IntakeFrontDownCommandTest() {
		test(new IntakeFrontDownCommand(realIntakeUpDownSubsystem), mockedFrontSolenoid, true);
	}

	@Test
	public void IntakeFrontUpCommandTest() {
		test(new IntakeFrontUpCommand(realIntakeUpDownSubsystem), mockedFrontSolenoid, false);
	}

}
