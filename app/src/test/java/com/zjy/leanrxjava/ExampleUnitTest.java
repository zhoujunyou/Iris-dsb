package com.zjy.leanrxjava;

import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testLakalaToaday() {
        long time = 1505232000000L;
        long aDayMillisTime = 24 * 60 * 60 * 1000L;
        long allowTime;
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long lakalaPointMillis = calendar.getTimeInMillis();
        if (currentHour >= 23) {
            allowTime = lakalaPointMillis;
        } else {
            allowTime = lakalaPointMillis - aDayMillisTime;
        }
        calendar.setTimeInMillis(time);
        int i = calendar.get(Calendar.HOUR_OF_DAY);
        assertEquals(time > allowTime, true);
    }

    @Test
    public void testRxDelay(){
        Observable.range(0, 9)
                .concatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull final Integer integer) throws Exception {
                        return Observable.zip(Observable.create(new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                                e.onNext(integer + 1);
                                //e.onComplete();
                            }
                        }), Observable.timer(1000, TimeUnit.MILLISECONDS), new BiFunction<Integer, Long, Integer>() {
                            @Override
                            public Integer apply(@NonNull Integer integer, @NonNull Long aLong) throws Exception {
                                return integer;
                            }
                        });
                    }
                })

                .subscribeWith(new DisposableObserver<Integer>() {


                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("delayTest |"+System.currentTimeMillis()+"| "+integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("delayTest error");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("delayTest complete");
                    }
                });


    }

    @Test
    public void testRetry(){
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {

                    }
                })
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                        return integer<3&&throwable instanceof IllegalArgumentException;
                    }
                })
        ;
    }

}