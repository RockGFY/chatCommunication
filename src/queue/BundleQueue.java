package queue;

import java.util.concurrent.LinkedBlockingQueue;

public class BundleQueue {

    private static LinkedBlockingQueue<Bundle> bundleQueue = new LinkedBlockingQueue<>();

    public static void add(Bundle bundle) {
        bundleQueue.add(bundle);
    }

    public static boolean isEmpty() {
        return bundleQueue.isEmpty();
    }

    public static Bundle poll() {
        return bundleQueue.poll();
    }
}
