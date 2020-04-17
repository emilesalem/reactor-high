import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;

public class Principal implements Subscriber<String> {

    private AtomicBoolean happy = new AtomicBoolean(false);

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String t) {
        logWithThreadName(t);
    }

    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onComplete() {
        happy.set(true);
        System.out.println("done");
    }

    public boolean isHappy() {
        return happy.get();
    }

    public void setHappy(boolean happy) {
        this.happy.set(happy);
    }

    private static void logWithThreadName(String message) {
        System.out.println(message + " (subscription thread: " + Thread.currentThread().getName() + ")");
    }
}
