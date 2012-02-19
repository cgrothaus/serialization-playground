package de.cgrothaus.hessianplayground;

public class HessianSerializerPerformanceTest extends AbstractSerializerPerformanceTest
{

	@Override
	protected Serializer initSerializer()
	{
		return new HessianSerializer();
	}

}
