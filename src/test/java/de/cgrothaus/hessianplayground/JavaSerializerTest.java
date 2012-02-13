package de.cgrothaus.hessianplayground;

public class JavaSerializerTest extends AbstractSerializerTest {

	@Override
	protected Serializer initSerializer() {
		return new JavaSerializer();
	}

}
