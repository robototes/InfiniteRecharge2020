package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.commands.intake.IntakeBothUpCommand;
import frc.team2412.robot.subsystems.LiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerMotorSubsystem motorSubsystem;
	private LiftMotorSubsystem midMotorSubsystem;
	private IndexerSensorSubsystem sensorSubsystem; 
	private IntakeLiftSubsystem intakeLiftSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexMotorSubsystem,
			LiftMotorSubsystem indexMidMotorSubsystem, IntakeLiftSubsystem intakeLiftSubsystem) {
		motorSubsystem = indexMotorSubsystem;
		this.midMotorSubsystem = indexMidMotorSubsystem;
		this.sensorSubsystem = indexerSensorSubsystem;

		addCommands(new IndexFrontShootCommand(indexMotorSubsystem, midMotorSubsystem),
				new ConditionalCommand(new WaitCommand(3), new WaitCommand(0),
						indexerSensorSubsystem::allFrontSensorsOff),
				new IndexBackShootCommand(indexMotorSubsystem, midMotorSubsystem), new WaitCommand(2));
	}

	@Override
	public void initialize() {
		super.initialize();
		motorSubsystem.setLifting(true);
		addCommands(
				
				// ya no
				new IntakeBothUpCommand(intakeLiftSubsystem),
				new IndexFrontShootCommand(motorSubsystem, midMotorSubsystem),
				new IndexBackShootCommand(motorSubsystem, midMotorSubsystem)
			);
		
		
		
	}

	@Override
	public void end(boolean cancel) {
		motorSubsystem.setLifting(false);
		motorSubsystem.stopAllMotors();
		midMotorSubsystem.down();
	}
}
