package frc.team2412.robot.commands.indexer;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerMotorBackSubsystem;
import frc.team2412.robot.subsystems.index.IndexerMotorFrontSubsystem;
import frc.team2412.robot.subsystems.index.IndexerMotorLiftSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexInCommand extends CommandBase {

	private IndexerMotorFrontSubsystem mf;
	private IndexerMotorBackSubsystem mb;
	private IndexerMotorLiftSubsystem ml;
	private IndexerSensorSubsystem ms;
	private IntakeLiftSubsystem ls;

	public IndexInCommand(IndexerSubsystemSuperStructure motorSubsystem, IntakeLiftSubsystem l) {
		ls = l;
		mf = motorSubsystem.getIndexerMotorFrontSubsystem();
		mb = motorSubsystem.getIndexerMotorBackSubsystem();
		ms = motorSubsystem.getIndexerSensorSubsystem();
		ml = motorSubsystem.getIndexerMotorLiftSubsystem();
		addRequirements(motorSubsystem);
	}

	@Override
	public void execute() {
		if(!ms.allInnerSensorsOn()){
			if(ms.getIndexFrontSensorValue()){
				mf.in();
				if(!ms.getIndexBackMidSensorValue()) mb.set(0.3);
				//ml.set(-0.1);
			} else if(ms.getIndexBackSensorValue()){
				mb.in();
				if(!ms.getIndexFrontMidSensorValue()) mf.set(0.3);
				//ml.set(-0.1);
			 }else{
				mf.stop();
				mb.stop();
			//	ml.stop();
				
			}
		} else{
			mf.stop();
			mb.stop();
		//	ml.stop();
		}
		
	}

	// @Override
	// public boolean isFinished() {
	// 	return true;
	// }
	@Override
	public void end(boolean cancel){
		mf.stop();
		mb.stop();
	}

}
