package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.subsystems.IndexerMidMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexShootCommand extends SequentialCommandGroup {

	private IndexerMotorSubsystem motorSubsystem;
	private IndexerMidMotorSubsystem midMotorSubsystem;
	private IndexerSensorSubsystem sensorSubsystem;

	public IndexShootCommand(IndexerSensorSubsystem indexerSensorSubsystem, IndexerMotorSubsystem indexMotorSubsystem,
			IndexerMidMotorSubsystem indexMidMotorSubsystem) {
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
				// new ParallelRaceGroup(new IndexSpitCommand(sensorSubsystem, motorSubsystem,
				// intakeSubsystem),
				// new WaitCommand(0.1)),
				// new IndexAllOffCommand(motorSubsystem),
				// new IndexMidMotorCommand(motorSubsystem),
				// new WaitCommand(0.3),
				new ParallelDeadlineGroup(new WaitCommand(1), new IndexAllOut(motorSubsystem, midMotorSubsystem)),
				new ParallelDeadlineGroup(new WaitCommand(1), new IndexMidMotorCommand(midMotorSubsystem)),
				// new IndexMidMotorCommand(motorSubsystem),
				// new WaitCommand(0.5),
				new IndexFrontShootCommand(motorSubsystem, midMotorSubsystem), new WaitCommand(2),
				// new IndexAllOffCommand(motorSubsystem),
				new IndexBackShootCommand(motorSubsystem, midMotorSubsystem), new WaitCommand(2)

		);
	}

	@Override
	public void end(boolean cancel) {
		motorSubsystem.setLifting(false);
		motorSubsystem.stopAllMotors();
		midMotorSubsystem.setDown();
	}
}
