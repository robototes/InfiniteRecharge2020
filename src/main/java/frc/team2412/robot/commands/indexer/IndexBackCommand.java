package frc.team2412.robot.commands.indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
public class IndexBackCommand extends CommandBase {

 private IndexerMotorSubsystem m_indexerMotorSubsystem;
 private IndexerSensorSubsystem m_indexerSensorSubsystem;

    public IndexBackCommand(IndexerMotorSubsystem indexerMotorSubsystem, IndexerSensorSubsystem indexerSensorSubsystem) {
     m_indexerMotorSubsystem = indexerMotorSubsystem;
     m_indexerSensorSubsystem = indexerSensorSubsystem;
     addRequirements(indexerMotorSubsystem);
    }

    @Override
    public void execute() {
        System.out.println("222");
     //m_indexerMotorSubsystem.setMidMotor(-0.2);
     if (m_indexerSensorSubsystem.getIndexFrontSensorValue())
      m_indexerMotorSubsystem.setFrontMotor(1);
     else
     m_indexerMotorSubsystem.setFrontMotor(0);
     if (!m_indexerSensorSubsystem.getIndexBackSensorValue() && m_indexerSensorSubsystem.getIndexBackInnerSensorValue()){
      m_indexerMotorSubsystem.setBackMotor(-1);
      //m_indexerMotorSubsystem.stopFrontPID(2);
     } else
      m_indexerMotorSubsystem.setBackMotor(0); 
    }

    @Override
    public boolean isFinished() {
     return true;
    }
   }
