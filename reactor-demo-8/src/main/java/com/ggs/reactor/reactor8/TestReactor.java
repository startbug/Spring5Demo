package com.ggs.reactor.reactor8;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: Starbug
 * @date: 2020/6/12 21:03
 */
public class TestReactor {
  public static void main(String[] args) {
    //just方法直接声明
//      Flux.just(1,2,3,4);
//      Mono.just(1);
//      //其他方法
//      Integer[] arr={1,2,3,4};
//      Flux.fromArray(arr);
//
//      List<Integer> list = Arrays.asList(arr);
//      Flux.fromIterable(list);
//
//      Stream<Integer> stream = list.stream();
//      Flux.fromStream(stream);

      Flux.just(1, 2, 3, 4).subscribe(System.out::println);
      Mono.just(1).subscribe(System.out::println);
  }
}
