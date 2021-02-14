package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestBase;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class LiftSubsystemTest extends TestBase {

	// Mock instance of Example Subsystem
	LiftSubsystem realLiftSubsystem;
	IndexerSubsystemSuperStructure mockedIndexerMotorSubsystem;
	DoubleSolenoid mockedLiftSolenoid;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedLiftSolenoid = mock(DoubleSolenoid.class);
		mockedIndexerMotorSubsystem = mock(IndexerSubsystemSuperStructure.class);

		realLiftSubsystem = new LiftSubsystem(mockedLiftSolenoid);
	}

	@Ignore
	@Test
	public void LiftDownCommandTest() {
		test(new LiftDownCommand(realLiftSubsystem, mockedIndexerMotorSubsystem), mockedLiftSolenoid, Value.kReverse);
	}

	@Ignore
	@Test
	public void LiftUpCommandTest() {
		test(new LiftUpCommand(realLiftSubsystem, mockedIndexerMotorSubsystem), mockedLiftSolenoid, Value.kForward);
	}

}
