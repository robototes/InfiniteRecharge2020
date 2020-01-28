package frc.team2412.robot.Subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.ControlPanelConstants;

public class ControlPanelColorSubsystem extends SubsystemBase {

	private ColorSensorV3 m_colorSensor;
	private Talon m_wheelMotor;
	private Color m_CurrentColor;
	private Color m_StartColor;
	private Color m_ColorUnderBar;
	private int rotationCount = 0;

	public ControlPanelColorSubsystem(ColorSensorV3 colorSensor, Talon motor) {
		this.m_colorSensor = colorSensor;
		this.m_wheelMotor = motor;
	}

	public void rotateControlPanel() {
		rotationCount = 0;
		m_StartColor = m_colorSensor.getColor();
		m_wheelMotor.set(0.5);
		while (rotationCount <= 7) {
			m_CurrentColor = m_colorSensor.getColor();
			if (m_CurrentColor.equals(m_StartColor)) {
				rotationCount++;
			}
		}
		m_wheelMotor.set(0);
	}

	public void setToTargetColor() {
		
		m_CurrentColor = m_colorSensor.getColor(); 
		System.out.println(colorToString(m_CurrentColor)); //Should be blue
			
		m_ColorUnderBar = getColorUnderBar(m_CurrentColor); 
		System.out.println(colorToString(m_ColorUnderBar) + " Color under bar returned"); //Should be red

		while (m_ColorUnderBar != ControlPanelConstants.TargetColor) {
			m_wheelMotor.set(0.25);
			m_CurrentColor = m_colorSensor.getColor();
			m_ColorUnderBar = getColorUnderBar(m_CurrentColor);
			System.out.println(colorToString(m_ColorUnderBar));
		}

		m_wheelMotor.set(0);
		
	}

	public Color getColorUnderBar(Color readColor) {
		if (readColor.equals(ControlPanelConstants.blueTarget)) {
			System.out.println("blue was put in");
			System.out.println("returning red");			
			return ControlPanelConstants.redTarget;
		} else if (readColor.equals(ControlPanelConstants.greenTarget)) {
			System.out.println("green was put in");
			return ControlPanelConstants.yellowTarget;
		} else if (readColor.equals(ControlPanelConstants.redTarget)) {
			System.out.println("red was put in");
			return ControlPanelConstants.blueTarget;
		} else if (readColor.equals(ControlPanelConstants.yellowTarget)) {
			System.out.println("yellow was put in");
			return ControlPanelConstants.greenTarget;
		} else {
			return Color.kBlack;
		}
	}
	
	public String colorToString(Color m_color) {
		if(m_color.equals(ControlPanelConstants.blueTarget))
			return "blue";
		if(m_color.equals(ControlPanelConstants.greenTarget))
			return "green";
		if(m_color.equals(ControlPanelConstants.redTarget))
			return "red";
		if(m_color.equals(ControlPanelConstants.yellowTarget))
			return "yellow";
		else {
			return "black";
		}
			
	}

}
