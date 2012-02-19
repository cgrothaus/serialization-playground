package de.cgrothaus.hessianplayground;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import de.cgrothaus.hessianplayground.data.*;

public abstract class AbstractSerializerPerformanceTest extends AbstractSerializerTest {

	private static final int INITIALIZATION_ROUNDS = 5000;
	private static final int MEASUREMENT_ROUNDS = 30000;

	@Test
	public void performanceOfSerializeSmallDataClass1() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return SmallDataClass.randomInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeSmallDataClass2() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return SmallDataClass.randomInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeSmallDataClass3() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return SmallDataClass.randomInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeSmallDataClass4() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return SmallDataClass.randomInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeMediumDataClass1() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return MediumDataClass.randomInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeMediumDataClass2() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return MediumDataClass.randomInstance();
			}
		}));
	}

	@Test
	public void performanceOfSerializeMediumDataClass3() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return MediumDataClass.randomInstance();
			}
		}));
	}

	@Test
	public void performanceOfSerializeLargeDataClass1() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return LargeDataClass.randomMultiInstance();
			}
		}));
	}
	
	@Test
	public void performanceOfSerializeLargeDataClass2() {
		runPerformanceTest(prepareTestInstances(new Generator() {
			public DataClass generate() {
				return LargeDataClass.randomMultiInstance();
			}
		}));
	}

	private LinkedList<DataClass> prepareTestInstances(Generator generator) {
		long start;
		long end;

		LinkedList<DataClass> toSerialize = new LinkedList<DataClass>();

		start = System.nanoTime();
		for (int i = 0; i < INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS; i++) {
			toSerialize.add(generator.generate());
		}
		end = System.nanoTime();
		System.out.println("Preparing " + (INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS) + " " + toSerialize.getFirst().getClass().getSimpleName()
				+ " test instances took " + (end - start) + " nanosec. (=" + ((end - start) / 1000000) + " ms)");

		Assert.assertEquals(INITIALIZATION_ROUNDS + MEASUREMENT_ROUNDS, toSerialize.size());
		return toSerialize;
	}

	private void runPerformanceTest(LinkedList<DataClass> toSerialize) {
		long start = 0;
		long end;
		int i = 0;
		for (DataClass d : toSerialize) {
			if (i == INITIALIZATION_ROUNDS) {
				start = System.nanoTime();
			}
			@SuppressWarnings("unused")
			byte[] serialized = serializer.serialize(d);
			i++;
		}
		end = System.nanoTime();

		System.out.println("Serializing " + MEASUREMENT_ROUNDS + " " + toSerialize.getFirst().getClass().getSimpleName() + " test instances took "
				+ (end - start) + " nanosec. (=" + ((end - start) / 1000000) + " ms) with serializer " + serializer.getClass().getSimpleName());
		System.out.println("==> that is " + (end - start) / MEASUREMENT_ROUNDS + " nanosec. per instance");
		
		System.gc(); // don't let GC affect the next test run
	}

	private static interface Generator {
		public DataClass generate();
	}
}
