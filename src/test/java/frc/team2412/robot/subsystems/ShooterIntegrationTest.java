package frc.team2412.robot.subsystems;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.robototes.math.Interpolable;
import com.robototes.math.InterpolatingDouble;

import frc.team2412.robot.subsystems.constants.ShooterConstants;

public class ShooterIntegrationTest {

	private class Data implements Interpolable<Data> {
		public InterpolatingDouble i;
		public InterpolatingDouble isq;

		public Data(double _i) {
			this(_i, _i * _i);
		}

		private Data(double _i, double _isq) {
			i = new InterpolatingDouble(_i);
			isq = new InterpolatingDouble(_isq);
		}

		public Data(InterpolatingDouble _i, InterpolatingDouble _isq) {
			i = _i;
			isq = _isq;
		}

		@Override
		public Data interpolate(Data other, double t) {
			return new Data(this.i.interpolate(other.i, t), this.isq.interpolate(other.isq, t));
		}
	}

	@Test
	public void testInterpolateInList() {
		List<Double> numberList = List.of(1.0, 2.0, 4.0);
		List<Data> interpolatingList = numberList.stream().map(i -> new Data(i)).collect(Collectors.toList());

		Data interpolatedValue = ShooterConstants.interpolateInList(interpolatingList, t -> t.i, 3);

		assertEquals("Value of index inside bounds", new InterpolatingDouble(3.0), interpolatedValue.i);
		assertEquals("Value of square inside bounds", new InterpolatingDouble(10.0), interpolatedValue.isq);

		Data interpolatedValueOutside = ShooterConstants.interpolateInList(interpolatingList, t -> t.i, 6);

		assertEquals("Value of index outside bounds", new InterpolatingDouble(6.0), interpolatedValueOutside.i);
		assertEquals("Value of square outside bounds", new InterpolatingDouble(28.0), interpolatedValueOutside.isq);

	}

}
