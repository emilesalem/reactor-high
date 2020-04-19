package rxhigh;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Kids extends Flux<Kids.Grader> {

    @Override
    public void subscribe(CoreSubscriber<? super Grader> actual) {
        Flux.range(0, 7)
                .flatMap(g ->
                        Flux.range(0, 1000)
                                .flatMap(s -> Mono.just(new Grader(g))
                                        .delayElement(Duration.ofSeconds((long) (Math.random() * 5)))
                                )
                ).subscribe(actual);
    }

    static public class Grader {
        private int grade;

        public int getGrade() {
            return this.grade;
        }

        public Grader(int grade) {
            this.grade = grade;
        }
    }
}




