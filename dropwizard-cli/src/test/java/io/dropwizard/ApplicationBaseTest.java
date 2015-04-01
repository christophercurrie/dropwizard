package io.dropwizard;

import io.dropwizard.setup.BootstrapBase;
import io.dropwizard.setup.EnvironmentBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationBaseTest {
    private static class FakeConfiguration extends ConfigurationBase {}

    private static class FakeApplication extends ApplicationBase<FakeConfiguration> {
        @Override
        public void run(FakeConfiguration configuration, EnvironmentBase environment) {}
    }

    private static class PoserApplication extends FakeApplication {}

    private static class WrapperApplication<C extends FakeConfiguration> extends ApplicationBase<C> {
        private final ApplicationBase<C> application;

        private WrapperApplication(ApplicationBase<C> application) {
            this.application = application;
        }

        @Override
        public void initialize(BootstrapBase<C> bootstrap) {
            this.application.initialize(bootstrap);
        }

        @Override
        public void run(C configuration, EnvironmentBase environment) throws Exception {
            this.application.run(configuration, environment);
        }
    }

    @Test
    public void hasAReferenceToItsTypeParameter() throws Exception {
        assertThat(new FakeApplication().getConfigurationClass())
                .isSameAs(FakeConfiguration.class);
    }

    @Test
    public void canDetermineConfiguration() throws Exception {
        assertThat(new PoserApplication().getConfigurationClass())
                .isSameAs(FakeConfiguration.class);
    }

    @Test
    public void canDetermineWrappedConfiguration() throws Exception {
        final PoserApplication application = new PoserApplication();
        assertThat(new WrapperApplication<>(application).getConfigurationClass())
                .isSameAs(FakeConfiguration.class);
    }
}
