package de.cgrothaus.hessianplayground;

import org.junit.*;

import de.cgrothaus.hessianplayground.data.*;

public abstract class AbstractSerializerObjectsizeTest extends AbstractSerializerTest {

	@Test
	public void testSmallDataClassEquals() {
		SmallDataClass sdc1 = new SmallDataClass("foobar", 23, 42);
		SmallDataClass sdc2 = new SmallDataClass("foobar", 23, 42);
		Assert.assertEquals(sdc1, sdc2);
	}
	
	@Test
	public void serializeSmallDataClass() {
		byte[] serialized = serializer.serialize(SmallDataClass.randomInstance());
		System.out.println("serializing a random SmallDataClass yields a byte array of size " + serialized.length + " with serializer " + serializer.getClass().getSimpleName());
	}
	
	@Test
	public void serializeDeserializeSmallDataClass() {
		SmallDataClass orig = SmallDataClass.randomInstance();
		byte[] serialized = serializer.serialize(orig);
		SmallDataClass deserialized = (SmallDataClass) serializer.deserialize(serialized);
		Assert.assertEquals(orig, deserialized);
	}
	
	@Test
	public void testMediumDataClassEquals() {
		MediumDataClass mdc1 = new MediumDataClass("foo", "bar", "baz", "abc", "xyz", -1, 0, 1, -100, 1000, -10000);
		MediumDataClass mdc2 = new MediumDataClass("foo", "bar", "baz", "abc", "xyz", -1, 0, 1, -100, 1000, -10000);
		Assert.assertEquals(mdc1, mdc2);
	}
	
	@Test
	public void serializeMediumDataClass() {
		byte[] serialized = serializer.serialize(MediumDataClass.randomInstance());
		System.out.println("serializing a random MediumDataClass yields a byte array of size " + serialized.length + " with serializer " + serializer.getClass().getSimpleName());
	}

	@Test
	public void serializeDeserializeMediumDataClass() {
		MediumDataClass orig = MediumDataClass.randomInstance();
		byte[] serialized = serializer.serialize(orig);
		MediumDataClass deserialized = (MediumDataClass) serializer.deserialize(serialized);
		Assert.assertEquals(orig, deserialized);
	}
	
	@Test
	public void testLargeDataClassEquals() {
		LargeDataClass ldc1 = new LargeDataClass("foo", "bar", "baz", "abc", "xyz", -1, 0, 1, -100, 1000, -10000);
		LargeDataClass ldc1a = LargeDataClass.randomSingleInstance();
		ldc1.getListChildren().add(ldc1a);
		LargeDataClass ldc1b = LargeDataClass.randomSingleInstance();
		ldc1.getMappedChildren().put("myKey", ldc1b);
		
		LargeDataClass ldc2 = new LargeDataClass("foo", "bar", "baz", "abc", "xyz", -1, 0, 1, -100, 1000, -10000);
		LargeDataClass ldc2a = new LargeDataClass(ldc1a);
		ldc2.getListChildren().add(ldc2a);
		LargeDataClass ldc2b = new LargeDataClass(ldc1b);
		ldc2.getMappedChildren().put("myKey", ldc2b);
		Assert.assertEquals(ldc1, ldc2);
	}
	
	@Test
	public void serializeLargeDataClass() {
		byte[] serialized = serializer.serialize(LargeDataClass.randomMultiInstance());
		System.out.println("serializing a random LargeDataClass sixpack yields a byte array of size " + serialized.length + " with serializer " + serializer.getClass().getSimpleName());
	}

	@Test
	public void serializeDeserializeLargeDataClass() {
		LargeDataClass orig = LargeDataClass.randomMultiInstance();
		byte[] serialized = serializer.serialize(orig);
		LargeDataClass deserialized = (LargeDataClass) serializer.deserialize(serialized);
		Assert.assertEquals(orig, deserialized);
	}

}
