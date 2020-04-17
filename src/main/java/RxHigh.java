import reactor.core.publisher.Flux;

public class RxHigh {

    public static void main(String[] args) {

        Principal principal = new Principal();

        School school = new School(new Kids());

        Flux.merge(
                school.bell(),
                school.grades()
        )
                .subscribe(principal);

        while (!principal.isHappy()) {
        }
    }
}
