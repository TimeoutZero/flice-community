package com.timeoutzero.flice.core.test.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

import org.junit.Before;

import com.timeoutzero.flice.core.init.CoreApplication;
import com.timeoutzero.flice.core.init.CoreConfiguration;
import com.timeoutzero.flice.core.resource.UserResource;

public class UserResourceTest {

	private final Environment environment = mock(Environment.class);
    private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
    private final CoreApplication application = new CoreApplication();
    private final CoreConfiguration config = new CoreConfiguration();

    @Before
    public void setup() throws Exception {
        when(environment.jersey()).thenReturn(jersey);
    }

   // @Test
    public void buildsAThingResource() throws Exception {
       
    	application.run(config, environment);

        verify(jersey).register(any(UserResource.class));
    }
}
