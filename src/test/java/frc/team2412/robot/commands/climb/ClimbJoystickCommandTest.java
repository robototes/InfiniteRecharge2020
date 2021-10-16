package frc.team2412.robot.commands.climb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClimbJoystickCommandTest {
    @Test
    public void getCorrectedJoystickValue() {
        assertTrue(ClimbJoystickCommand.m_joystickDeadZone < 1.0);

        final double epsilon = 0.01;
        assertEquals(0.0, ClimbJoystickCommand.getCorrectedJoystickValue(0.0), epsilon);

        final double deadZone = ClimbJoystickCommand.m_joystickDeadZone;

        assertEquals(0.0, ClimbJoystickCommand.getCorrectedJoystickValue(deadZone / 2), epsilon);
        assertEquals(0.0, ClimbJoystickCommand.getCorrectedJoystickValue(deadZone), epsilon);
        assertEquals(0.0, ClimbJoystickCommand.getCorrectedJoystickValue(-deadZone / 2), epsilon);
        assertEquals(0.0, ClimbJoystickCommand.getCorrectedJoystickValue(-deadZone), epsilon);

        assertEquals(0.02, ClimbJoystickCommand.getCorrectedJoystickValue(deadZone + 0.02), epsilon);
        assertEquals(-0.02, ClimbJoystickCommand.getCorrectedJoystickValue(-deadZone + -0.02), epsilon);

        assertEquals(0.1 / (1.0 - deadZone), ClimbJoystickCommand.getCorrectedJoystickValue(deadZone + 0.1), epsilon);
        assertEquals(-0.1 / (1.0 - deadZone), ClimbJoystickCommand.getCorrectedJoystickValue(-deadZone - 0.1), epsilon);

        assertEquals(0.5, ClimbJoystickCommand.getCorrectedJoystickValue(deadZone + (1.0 - deadZone) / 2), epsilon);
        assertEquals(-0.5, ClimbJoystickCommand.getCorrectedJoystickValue(-deadZone + (-1.0 + deadZone) / 2), epsilon);

        assertEquals(0.98, ClimbJoystickCommand.getCorrectedJoystickValue(0.98), epsilon);
        assertEquals(-0.98, ClimbJoystickCommand.getCorrectedJoystickValue(-0.98), epsilon);

        assertEquals(1.0, ClimbJoystickCommand.getCorrectedJoystickValue(1.0), epsilon);
        assertEquals(-1.0, ClimbJoystickCommand.getCorrectedJoystickValue(-1.0), epsilon);
    }
}
