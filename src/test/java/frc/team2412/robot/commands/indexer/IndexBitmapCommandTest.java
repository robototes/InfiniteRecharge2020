package frc.team2412.robot.commands.indexer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// Test the index bitmap command
public class IndexBitmapCommandTest {

	// Test that every possible bitmap value has one and only one matching
	// IndexCommandEntry
	@Test
	public void IndexCommandEntry() {
		for (int testBitmap = 0; testBitmap <= IndexBitmapCommand.VALID_SENSOR_BITS; testBitmap++) {
			int commandCount = 0;
			for (IndexBitmapCommand.IndexCommandEntry entry : IndexBitmapCommand.IndexCommandEntry.values()) {
				if (entry.expectedBits == (testBitmap & entry.validBits)) {
					commandCount++;
				}
			}

			assertEquals(1, commandCount);
		}
	}
}
