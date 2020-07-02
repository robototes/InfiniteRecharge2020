package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revrobotics.CANSparkMax;
import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;
import frc.team2412.robot.subsystems.constants.IntakeConstants;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeOnOffSubsystemTest {

	// Mock instance of Example Subsystem
	CANSparkMax mockedIntakeFrontMotor = mock(CANSparkMax.class);
	CANSparkMax mockedIntakeBackMotor = mock(CANSparkMax.class);

	IntakeMotorSubsystem realIntakeMotorOnOffSubsystem = new IntakeMotorSubsystem(mockedIntakeFrontMotor,
			mockedIntakeBackMotor);

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

		reset(mockedIntakeFrontMotor);
		reset(mockedIntakeBackMotor);
	}

	public <T extends Command> void IntakeTest(Function<IntakeMotorSubsystem, T> commandContructor,
			CANSparkMax changedMotor, double wantedValue) {
		// Create command
		Command intakeCommand = commandContructor.apply(realIntakeMotorOnOffSubsystem);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(intakeCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		verify(changedMotor, times(1)).set(wantedValue);

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	@Test
	public void IntakeBackOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		IntakeTest(IntakeBackOffCommand::new, mockedIntakeBackMotor, 0);
	}

	@Test
	public void IntakeBackOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		IntakeTest(IntakeBackInCommand::new, mockedIntakeBackMotor, IntakeConstants.MAX_INTAKE_SPEED);
	}

	@Test
	public void IntakeFrontOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		IntakeTest(IntakeFrontOffCommand::new, mockedIntakeFrontMotor, 0);
	}

	@Test
	public void IntakeFrontOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		IntakeTest(IntakeFrontInCommand::new, mockedIntakeFrontMotor, IntakeConstants.MAX_INTAKE_SPEED);
	}

}
