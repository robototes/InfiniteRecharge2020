package frc.team2412.robot.subsystems.constants;

import edu.wpi.first.wpilibj.util.Color;

public class ControlPanelConstants {

	public static enum Colors {

		BLUE(blueTarget, "BLUE"), GREEN(greenTarget, "GREEN"), RED(redTarget, "RED"), YELLOW(yellowTarget, "YELLOW");

		public Color color;

		public String name;

		private Colors(Color color, String name) {
			this.color = color;
			this.name = name;
		}

		public String toString() {
			return name;
		}

	}

	public static Color blueTarget = new Color(0, 0, 255);
	public static Color greenTarget = new Color(0, 255, 0);
	public static Color redTarget = new Color(255, 0, 0);
	public static Color yellowTarget = new Color(0, 255, 255);

	public static Color TargetColor = blueTarget;
}
