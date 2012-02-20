package de.cgrothaus.hessianplayground;

public class HandmadeDataSerializableSerializerObjectsizeTest extends AbstractSerializerObjectsizeTest {

	@Override
	protected Serializer initSerializer() {
		return new HandmadeDataSerializableSerializer();
	}

}
