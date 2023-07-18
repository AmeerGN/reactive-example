package com.baeldungtest.reactiveexample.service;

import com.baeldungtest.reactiveexample.model.Foo;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FooService {

  private static final Stream<Foo> fooStream = Stream.of(new Foo("1", "A"),
                                                          new Foo("2", "B"),
                                                          new Foo("3", "C"));

  public Flux<Foo> getAllFoos() {
    return Flux.fromStream(fooStream);
  }

}
