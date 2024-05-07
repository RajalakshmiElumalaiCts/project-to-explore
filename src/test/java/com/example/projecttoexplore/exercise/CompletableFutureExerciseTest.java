package com.example.projecttoexplore.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@ExtendWith(MockitoExtension.class)
public class CompletableFutureExerciseTest {

    @Mock
    private CompletableFutureExercise cfe;

    @Test
    public void testCompletableFuture() {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> cfe.getRandomNum());
        System.out.println("Random Number generated START" + "with ---->" +Thread.currentThread().getName() );

        cf.thenAccept((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName()));
        cf.thenAccept((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName()));

    }

    @Test
    public void testCompletableFutureWithMono() {
        Mono mono = Mono.just(cfe.getRandomNum());
        System.out.println("Random Number generated START" + "with ---->" +Thread.currentThread().getName() );

        mono.doOnNext((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName())).subscribe();
        mono.subscribe((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName()));


        Mono mono2 = Mono.fromSupplier(() -> cfe.getRandomNum());

        mono2.doOnNext((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName())).subscribe();
        mono2.doOnNext((num) -> System.out.println("Random Number generated " + num + "with ---->" +Thread.currentThread().getName())).subscribe();

    }
}
