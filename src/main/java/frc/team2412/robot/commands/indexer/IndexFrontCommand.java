package frc.team2412.robot.commands.indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
public class IndexFrontCommand extends CommandBase {
 private IndexerMotorSubsystem m_indexerMotorSubsystem;
 private IndexerSensorSubsystem m_indexerSensorSubsystem;
 public IndexFrontCommand(IndexerMotorSubsystem indexerMotorSubsystem, IndexerSensorSubsystem indexerSensorSubsystem) {
  m_indexerMotorSubsystem = indexerMotorSubsystem;
  m_indexerSensorSubsystem = indexerSensorSubsystem;
  addRequirements(indexerMotorSubsystem);
 }
 private boolean e = true;
 @Override
 public void execute() {
     System.out.println("222");
  //m_indexerMotorSubsystem.setMidMotor(-0.2);
  if (m_indexerSensorSubsystem.getIndexFrontSensorValue()){
   //m_indexerMotorSubsystem.setFrontMotor(-1);
   m_indexerMotorSubsystem.stopFrontPID(-7);
   //if (!m_indexerSensorSubsystem.getIndexBackInnerSensorValue()){
    m_indexerMotorSubsystem.stopBackPID(7);
   //else
    //m_indexerMotorSubsystem.setBackMotor(0);
  }else
   m_indexerMotorSubsystem.setFrontMotor(0);
  if(e && m_indexerSensorSubsystem.allInnerSensorsOn()){
      m_indexerMotorSubsystem.stopFrontPID(-1);
      //m_indexerMotorSubsystem.stopBackPID(1);
      e=false;
  }
   //if(m_indexerSensorSubsystem.getIndexFrontInnerSensorValue())
    //m_indexerMotorSubsystem.setMidMotor(-0.3);

 }
 @Override
 public boolean isFinished() {
  return true;
 }
}