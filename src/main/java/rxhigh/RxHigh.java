package rxhigh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class RxHigh {
    protected static Logger logger;

    static {
        logger = LoggerFactory.getLogger(RxHigh.class.getName());
    }

    public static void main(String[] args) {

        School school = new School(new Kids());

        // notice we subscribe to the bell in more than one context;
        // this is to show a Publisher will be executed for every subscriber
        // on the thread where each subscriber is executed;
        // the .toStream operator is part of Reactor's blocking API.
        // it lets the main thread wait for the Flux to complete.
        Flux.merge(
                school.bell(),
                school.grades()
        )
                .toStream()
                .forEach(logger::info);
    }
}
