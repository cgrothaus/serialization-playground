package de.cgrothaus.hessianplayground;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import de.cgrothaus.hessianplayground.data.*;

public abstract class AbstractSerializerPerformanceTest extends AbstractSerializerTest {

	private static final int INITIALIZATION_ROUNDS = 10000;
	private static final int MEASUREMENT_ROUNDS = 50000;

	@Test
	public void performanceOfSerializeSmallDataClass() {
		runPerformanceTest(new Generator() {
			public DataClass generate() {
				return SmallDataClass.randomInstance();
			}
			public String getTestClassName() {
				return SmallDataClass.class.getSimpleName();
			}
		}, 20);
	}

	@Test
	public void performanceOfSerializeMediumDataClass() {
		runPerformanceTest(new Generator() {
			public DataClass generate() {
				return MediumDataClass.randomInstance();
			}
			public String getTestClassName() {
				return MediumDataClass.class.getSimpleName();
			}
		}, 20);
	}

	@Test
	public void performanceOfSerializeLargeDataClass() {
		runPerformanceTest(new Generator() {
			public DataClass generate() {
				return LargeDataClass.randomMultiInstance();
			}
			public String getTestClassName() {
				return LargeDataClass.class.getSimpleName();
			}
		}, 10);
	}

	private LinkedList<DataClass> prepareTestInstances(Generator generator) {
		//long start;
		//long end;

		LinkedList<DataClass> toSerialize = new LinkedList<DataClass>();

		//start = System.nanoTime();
		for (int i = 0; i < INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS; i++) {
			toSerialize.add(generator.generate());
		}
		//end = System.nanoTime();
		// System.out.println("Preparing " + (INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS) + " " + toSerialize.getFirst().getClass().getSimpleName()
		//		+ " test instances took " + (end - start) + " nanosec. (=" + ((end - start) / 1000000) + " ms)");

		Assert.assertEquals(INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS, toSerialize.size());
		return toSerialize;
	}

	private void runPerformanceTest(Generator generator, int numTestRuns) {
		long sumDurNanos = 0;
		for (int i = 0; i < numTestRuns; i++) {
			sumDurNanos += runSinglePerformanceTestRun(generator);
		}
		long avgDurNanos = sumDurNanos / numTestRuns;
		long avgDurMillis = avgDurNanos / 1000000;
		long durPerInstanceNanos = avgDurNanos / MEASUREMENT_ROUNDS;
		long instancesPerSec = 1000000000 / durPerInstanceNanos; // == 1/durPerInstanceNanos * 10^9 

		System.out.println("Serializing " + MEASUREMENT_ROUNDS + " " + generator.getTestClassName() + " test instances took " + avgDurNanos + " nanosec. (="
				+ avgDurMillis + " ms) with serializer " + serializer.getClass().getSimpleName() + " (average of " + numTestRuns + " test runs)");
		System.out.println("==> that is " + durPerInstanceNanos + " nanosec. per instance, or " + instancesPerSec + " instances per sec.");

		System.gc(); // don't let GC affect the next test run
	}

	/**
	 * @param generator
	 *            test data generator
	 * @return the duration of serializing MEASUREMENT_ROUNDS test instances, in nano sec.
	 */
	private long runSinglePerformanceTestRun(Generator generator) {
		long start = 0;
		long end;
		int i = 0;
		for (DataClass d : prepareTestInstances(generator)) {
			if (i == INITIALIZATION_ROUNDS) {
				start = System.nanoTime();
			}
			@SuppressWarnings("unused")
			byte[] serialized = serializer.serialize(d);
			i++;
		}
		end = System.nanoTime();
		return end - start;
	}

	private static interface Generator {
		public DataClass generate();
		public String getTestClassName();
	}
}
