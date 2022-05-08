package jak.meetup.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class AppLifecycle {

    private static final Logger LOGGER = Logger.getLogger("FILE");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Application started");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Application terminated");
    }
}