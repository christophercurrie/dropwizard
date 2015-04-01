package io.dropwizard;

import io.dropwizard.logging.LoggingFactory;
import io.dropwizard.setup.BootstrapBase;
import io.dropwizard.setup.EnvironmentBase;
import io.dropwizard.util.Generics;

public abstract class ApplicationBase<T extends ConfigurationBase> {
    static {
        // make sure spinning up Hibernate Validator doesn't yell at us
        LoggingFactory.bootstrap();
    }

    /**
     * Returns the {@link Class} of the configuration class type parameter.
     *
     * @return the configuration class
     * @see Generics#getTypeParameter(Class, Class)
     */
    public final Class<T> getConfigurationClass() {
        return Generics.getTypeParameter(getClass(), ConfigurationBase.class);
    }

    /**
     * Returns the name of the application.
     *
     * @return the application's name
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Initializes the application bootstrap.
     *
     * @param bootstrap the application bootstrap
     */
    public void initialize(BootstrapBase<T> bootstrap) {

    }

    public void run(T configuration, EnvironmentBase environmentBase) throws Exception {

    }
}
