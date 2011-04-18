package com.aric.esb.ejb;

import java.util.Properties;

import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.arguments.Argument;
import com.aric.esb.arguments.SimpleArgument;
import com.aric.esb.resourcefactory.EjbResourceFactory;
import com.aric.esb.resourcefactory.ResourceFactory;

public class EjbCallerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCallWithArgumentWithoutResultFromWeblogic() throws Exception {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");
		ResourceFactory resourceFactory = new EjbResourceFactory(env);
		EjbCaller caller = new EjbCaller(
				"HelloWorld#com.aric.samples.ejb3.HelloWorld",
				"sayHello", resourceFactory);
		ArgumentBundle argumentBundle = new ArgumentBundle(
				new Argument[] { new SimpleArgument("name", "Dursun") });
		final Object[] call = caller.call(argumentBundle);
		for (Object object : call) {
			System.out.println(object);
		}
	}
	@Test
	public void testCallWithOutArgumentWithResultFromWeblogic() throws Exception {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
		"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");
		ResourceFactory resourceFactory = new EjbResourceFactory(env);
		EjbCaller caller = new EjbCaller(
				"HelloWorld#com.aric.samples.ejbsample.HelloWorldBeanRemote",
				"sayHello", resourceFactory);
		ArgumentBundle argumentBundle = new ArgumentBundle(
				new Argument[] { });
		final Object[] call = caller.call(argumentBundle);
		for (Object object : call) {
			System.out.println(object);
		}
	}

}
