package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.IntakeConstants.MAX_INTAKE_SPEED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.revrobotics.CANSparkMax;
import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IntakeOnOffSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	IntakeMotorSubsystem realIntakeMotorOnOffSubsystem;
	CANSparkMax mockedIntakeFrontMotor;
	CANSparkMax mockedIntakeBackMotor;

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

		realIntakeMotorOnOffSubsystem = new IntakeMotorSubsystem(mockedIntakeFrontMotor, mockedIntakeBackMotor);
	}

	@Test
	public void IntakeBackOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		test(new IntakeBackOffCommand(realIntakeMotorOnOffSubsystem), mockedIntakeBackMotor, 0);
	}

	@Test
	public void IntakeBackOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		test(new IntakeBackInCommand(realIntakeMotorOnOffSubsystem), mockedIntakeBackMotor, MAX_INTAKE_SPEED);
	}

	@Test
	public void IntakeFrontOffCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		test(new IntakeFrontOffCommand(realIntakeMotorOnOffSubsystem), mockedIntakeFrontMotor, 0);
	}

	@Test
	public void IntakeFrontOnCommandOnIntakeMotorOnOffSubsystemCallsMotorSet() {
		test(new IntakeFrontInCommand(realIntakeMotorOnOffSubsystem), mockedIntakeFrontMotor, MAX_INTAKE_SPEED);
	}

}
