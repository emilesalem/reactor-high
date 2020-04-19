package rxhigh;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class School {

    private short SCHOOL_START_TIME = 3;

    private Kids kids;

    public School(Kids kids) {
        this.kids = kids;
    }

    public Flux<String> grades() {
        return kids
                .takeUntilOther(s -> bell().subscribe(s))
                .groupBy(x -> x.getGrade())
                .flatMap(g ->
                        g.reduce(new Grade(g.key()), (acc, curr) -> {
                            int arrived = acc.getArrived();
                            acc.setArrived(++arrived);
                            return acc;
                        })
                )
                .map(x -> "grade: " + x.getGrade() + " arrived: " + x.getArrived());
    }

    public Mono<String> bell() {
        return Mono.just("rrrrRRRRRING")
                .delayElement(Duration.ofSeconds(SCHOOL_START_TIME))
                .doOnNext(x -> RxHigh.logger.debug(x));
    }

    static private class Grade {
        private int grade;

        private int arrived = 0;

        public int getGrade() {
            return this.grade;
        }

        public int getArrived() {
            return this.arrived;
        }

        public void setArrived(int arrived) {
            this.arrived = arrived;
        }

        public Grade(int grade) {
            this.grade = grade;
        }
    }
}
