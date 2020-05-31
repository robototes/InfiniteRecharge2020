package frc.team2412.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import static frc.team2412.robot.subsystems.constants.ControlPanelConstants.*;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ControlPanelConstants;
import io.github.oblarg.oblog.Loggable;

public class ControlPanelColorSubsystem extends SubsystemBase implements Loggable {

	private ColorSensorV3 m_colorSensor;
	private ColorMatch m_colorMatcher = new ColorMatch();
	private WPI_TalonFX m_wheelMotor;

	private Color m_currentColor;

	private Color m_startColor;
	private Color m_ColorUnderBar;
	private int rotationCount = 0;

	public ControlPanelColorSubsystem(ColorSensorV3 colorSensor, WPI_TalonFX motor) {
		this.m_colorSensor = colorSensor;
		this.m_wheelMotor = motor;
		
		m_colorMatcher.addColorMatch(redTarget);
		m_colorMatcher.addColorMatch(blueTarget);
		m_colorMatcher.addColorMatch(greenTarget);
		m_colorMatcher.addColorMatch(yellowTarget);
	}

	public Color colorMatch(Color detectedColor) {
		ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
		return match.color;

	}

	public String colorToString(Color m_color) {
		if (m_color.equals(blueTarget))
			return "blue";
		if (m_color.equals(greenTarget))
			return "green";
		if (m_color.equals(redTarget))
			return "red";
		if (m_color.equals(yellowTarget))
			return "yellow";
		else {
			return "Unknown";
		}
	}

	public Color getColorUnderBar(Color readColor) {
		if (readColor.equals(blueTarget)) {
			return ControlPanelConstants.redTarget;
		} else if (readColor.equals(greenTarget)) {
			return ControlPanelConstants.yellowTarget;
		} else if (readColor.equals(redTarget)) {
			return ControlPanelConstants.blueTarget;
		} else if (readColor.equals(yellowTarget)) {
			return ControlPanelConstants.greenTarget;
		} else {
			return Color.kBlack;
		}
	}

	public void rotateControlPanel() {
		rotationCount = 0;
		m_startColor = colorMatch(m_colorSensor.getColor());
		m_wheelMotor.set(0.5);
		while (rotationCount <= 7) {
			m_currentColor = colorMatch(m_colorSensor.getColor());
			if (m_currentColor.equals(m_startColor)) {
				rotationCount++;
			}
		}
		m_wheelMotor.set(0);
	}

	public void setToTargetColor() {

		m_currentColor = colorMatch(m_colorSensor.getColor());
		m_ColorUnderBar = getColorUnderBar(m_currentColor);

		while (m_ColorUnderBar != ControlPanelConstants.TargetColor) {
			m_wheelMotor.set(0.25);
			m_currentColor = colorMatch(m_colorSensor.getColor());
			m_ColorUnderBar = getColorUnderBar(m_currentColor);
		}

		m_wheelMotor.set(0);

	}
}
