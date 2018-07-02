package com.onlylovetianya.salary;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="zhuo.li@ximalaya.com">Zhuo.li</a>
 * @date 2018/6/27
 */

//@Slf4j
public class Counter {

    volatile long value = 3000l;


    private static final Unsafe unsafe;
    private static final long valueOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            valueOffset = unsafe.objectFieldOffset
                    (Counter.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public boolean tryDelive(final long acquires) {
        return delive(acquires) > 0;
    }

    public long delive(final long acquires) {

        for (; ; ) {
            long available = get();
            long remaining = available - acquires;
            if (remaining < 0 ||
                    compareAndSet(available, remaining))
                return remaining;
        }
    }

    public final long get() {
        return value;
    }

    public final boolean compareAndSet(long expect, long update) {
        return unsafe.compareAndSwapLong(this, valueOffset, expect, update);
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch la = new CountDownLatch(3);

        Counter counter = new Counter();

        AtomicInteger dd = new AtomicInteger(0);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                la.countDown();
                while (true) {
                    int dz = new Random().nextInt() >>> 24;
                    if (counter.tryDelive(dz)) {
                        System.out.println("发工资 {}  ,已发 {}: " + dz + " " + dd.addAndGet(dz));
//                        log.info("发工资 {}  ,已发 {}: ", +dz, dd.addAndGet(dz));
                    } else {
                        System.out.println("余额 {} 不足发{}" + counter.get() + " " + dz);
//                        log.info("余额 {} 不足发{}", counter.get(), dz);
                        break;
                    }
                }

            }).start();
        }

        la.await();


        Thread.currentThread().join();


    }
}
