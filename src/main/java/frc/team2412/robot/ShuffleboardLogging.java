package frc.team2412.robot;

import java.awt.Color;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

public class ShuffleboardLogging {

	private final ShuffleboardTab m_driverView;

	private NetworkTableEntry m_outerGoalEntry, m_innerGoalEntry;

	private SimpleWidget m_outerGoalWidget, m_innerGoalWidget;

	private boolean outerGoalAimed, outerGoalPossible, innerGoalPossible, innnerGoalAimed;

	public ShuffleboardLogging() {
		m_driverView = Shuffleboard.getTab("Driver View");
		configureDriverView();
	}

	public void periodic() {
		driverViewPeriodic();
	}

	private void configureDriverView() {
		m_outerGoalWidget = m_driverView.add("Outer Goal possible", false).withPosition(0, 0)
				.withProperties(Map.of("colorWhenFalse", "white")).withSize(4, 4);

		m_outerGoalEntry = m_outerGoalWidget.getEntry();

		m_innerGoalWidget = m_driverView.add("Inner Goal Possible", false).withPosition(1, 1).withSize(2, 2)
				.withProperties(Map.of("colorWhenFalse", "white"));

		m_innerGoalEntry = m_innerGoalWidget.getEntry();

	}

	private void driverViewPeriodic() {
		Color outerGoalColor = Color.white;

		if (outerGoalAimed) {
			outerGoalColor.equals(Color.GREEN);
		} else if (outerGoalPossible) {
			outerGoalColor.equals(Color.YELLOW);
		}

		m_outerGoalWidget.withProperties(Map.of("colorWhenTrue", outerGoalColor));
		m_outerGoalEntry.setBoolean(true);

		Color innerGoalColor = Color.white;

		if (innnerGoalAimed) {
			innerGoalColor.equals(Color.green);
		} else if (innerGoalPossible) {
			innerGoalColor.equals(Color.yellow);
		}

		m_innerGoalWidget.withProperties(Map.of("colorwhenTrue", innerGoalColor));
		m_innerGoalEntry.setBoolean(true);

	}

}
