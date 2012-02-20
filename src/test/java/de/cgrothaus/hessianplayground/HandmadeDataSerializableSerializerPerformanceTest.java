package de.cgrothaus.hessianplayground;

public class HandmadeDataSerializableSerializerPerformanceTest extends AbstractSerializerPerformanceTest
{

	@Override
	protected Serializer initSerializer()
	{
		return new HandmadeDataSerializableSerializer();
	}

}
