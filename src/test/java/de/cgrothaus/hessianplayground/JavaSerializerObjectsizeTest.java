package de.cgrothaus.hessianplayground;

public class JavaSerializerObjectsizeTest extends AbstractSerializerObjectsizeTest {

	@Override
	protected Serializer initSerializer() {
		return new JavaSerializer();
	}

}
