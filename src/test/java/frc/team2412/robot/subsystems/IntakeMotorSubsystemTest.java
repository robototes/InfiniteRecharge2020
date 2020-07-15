package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.IntakeConstants.MAX_INTAKE_SPEED;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.revrobotics.CANSparkMax;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOutCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOutCommand;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeMotorSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	IntakeMotorSubsystem realIntakeMotorOnOffSubsystem;
	CANSparkMax mockedIntakeFrontMotor;
	CANSparkMax mockedIntakeBackMotor;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedIntakeFrontMotor = mock(CANSparkMax.class);
		mockedIntakeBackMotor = mock(CANSparkMax.class);

		realIntakeMotorOnOffSubsystem = new IntakeMotorSubsystem(mockedIntakeFrontMotor, mockedIntakeBackMotor);
	}

	@Test
	public void IntakeBackOutCommandTest() {
		test(new IntakeBackOutCommand(realIntakeMotorOnOffSubsystem), mockedIntakeBackMotor, -MAX_INTAKE_SPEED);
	}

	
	@Test
	public void IntakeBackOffCommandTest() {
		test(new IntakeBackOffCommand(realIntakeMotorOnOffSubsystem), mockedIntakeBackMotor, 0);
	}

	@Test
	public void IntakeBackOnCommandTest() {
		test(new IntakeBackInCommand(realIntakeMotorOnOffSubsystem), mockedIntakeBackMotor, MAX_INTAKE_SPEED);
	}
	
	@Test
	public void IntakeFrontOutCommandTest() {
		test(new IntakeFrontOutCommand(realIntakeMotorOnOffSubsystem), mockedIntakeFrontMotor, -MAX_INTAKE_SPEED);
	}

	@Test
	public void IntakeFrontOffCommandTest() {
		test(new IntakeFrontOffCommand(realIntakeMotorOnOffSubsystem), mockedIntakeFrontMotor, 0);
	}

	@Test
	public void IntakeFrontOnCommandTest() {
		test(new IntakeFrontInCommand(realIntakeMotorOnOffSubsystem), mockedIntakeFrontMotor, MAX_INTAKE_SPEED);
	}

}
