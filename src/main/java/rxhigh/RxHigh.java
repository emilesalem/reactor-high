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

        Flux.merge(
                school.bell(),
                school.grades()
        )
                .toStream()
                .forEach(logger::info);
    }
}
