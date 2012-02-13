package de.cgrothaus.hessianplayground;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.cgrothaus.hessianplayground.data.MediumDataClass;
import de.cgrothaus.hessianplayground.data.SmallDataClass;

public abstract class AbstractSerializerTest {

	private Serializer serializer;
	
	protected abstract Serializer initSerializer();
	
	@Before
	public void setUp() {
		this.serializer = initSerializer();
	}
	
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
	public void serializeSmallMediumClass() {
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
	

}
