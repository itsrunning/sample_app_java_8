package skeleton;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.cli.ServerCommand;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.lifecycle.ServerLifecycleListener;
import net.sourceforge.argparse4j.inf.Namespace;
import org.eclipse.jetty.server.Server;

/**
 * Based on {@see com.yammer.dropwizard.testing.junit.DropwizardServiceRule}
 */
public class DropwizardTestSupport<C extends Configuration> {

    private final Class<? extends Service<C>> serviceClass;
    private final String configPath;

    private C configuration;
    private Service<C> service;
    private Environment environment;
    private Server jettyServer;

    public DropwizardTestSupport(Class<? extends Service<C>> serviceClass, String configPath) {
        this.serviceClass = serviceClass;
        this.configPath = configPath;
    }


    public void startIfRequired() {
        if (jettyServer != null) {
            return;
        }

        try {
            service = serviceClass.newInstance();

            final Bootstrap<C> bootstrap = new Bootstrap<C>(service) {
                @Override
                public void runWithBundles(C configuration, Environment environment) throws Exception {
                    environment.addServerLifecycleListener(new ServerLifecycleListener() {
                        @Override
                        public void serverStarted(Server server) {
                            jettyServer = server;
                        }
                    });
                    DropwizardTestSupport.this.configuration = configuration;
                    DropwizardTestSupport.this.environment = environment;
                    super.runWithBundles(configuration, environment);
                }
            };

            service.initialize(bootstrap);
            final ServerCommand<C> command = new ServerCommand<C>(service);
            final Namespace namespace = new Namespace(ImmutableMap.<String, Object>of("file", configPath));
            command.run(bootstrap, namespace);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (jettyServer != null) {
            try {
                jettyServer.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public C getConfiguration() {
        return configuration;
    }

    public int getLocalPort() {
        return jettyServer.getConnectors()[0].getLocalPort();
    }

    public String getEndpoint() {
        return "http://localhost:" + getLocalPort();
    }

    public ObjectMapper getObjectMapper() {
        return getEnvironment().getObjectMapperFactory().build();
    }

    public Environment getEnvironment() {
        return environment;
    }
}