package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class IndexerSubsystem extends SubsystemBase {
	//put in const + add pointers
	public CANSparkMax indexBackMotor;
	public CANSparkMax indexFrontMotor;
	public CANSparkMax indexMidMotor;
	public SpeedControllerGroup indexMotors;
	public SpeedControllerGroup indexSideMotors;
	
	public DigitalInput BB;
	public DigitalInput BT;
	public DigitalInput T;
	public DigitalInput FT;
	public DigitalInput FF;
	
	public IndexerSubsystem() {
		//Motors
		indexBackMotor = RobotMap.indexFrontMotor;
		indexFrontMotor = RobotMap.indexBackMotor;
		indexMidMotor = RobotMap.indexMidMotor;
		indexMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor, indexMidMotor);
		indexSideMotors = new SpeedControllerGroup(indexFrontMotor, indexBackMotor);
		
		//Sensors
		BB = RobotMap.BB;
		BT = RobotMap.BT;
		T = RobotMap.T;
		FT = RobotMap.FT;
		FF = RobotMap.FF;
	}
	
	//Motors
	public void frontMotorPower(double speed) {
		indexFrontMotor.set(speed);
	}
	
	public void backMotorPower(double speed) {
		indexBackMotor.set(speed);
	}
	
	public void midMotorPower(double speed) {
		indexMidMotor.set(speed);
	}
		
	public void stopAll() {
		indexMotors.set(0.0);
	}
	public void stopSides() {
		indexSideMotors.set(0.0);
	}
	
	//Sensors
	public boolean frontSensor() {
		return FF.get();
	 //read front sensor value and convert to boolean
	}
	public boolean frontMidSensor(){
		return FT.get();
	  //read frontMid sensor value and convert to boolean
	}
	public boolean midSensor(){
		return T.get();
	  //read mid sensor value and convert to boolean
	}
	public boolean rearMidSensor(){
		return BT.get();
	  //read rearMid sensor value and convert to boolean
	}
	public boolean rearSensor(){
		return BB.get();
	  //read rear sensor value and convert to boolean
	}
	public boolean allSensorsOn(){
		if(frontSensor() && frontMidSensor()
				&& midSensor() 
				&&rearMidSensor() && rearSensor()) {
			return true;
		}
		
		return false;
	  //check if all sensors are on
	}
	
	public boolean allSensorsOff(){	
		if(frontSensor() && frontMidSensor()
				&& midSensor() 
				&&rearMidSensor() && rearSensor()) {
			return false;
		}
		
		return true;
	  //check if all sensors are on
	}
	
}
