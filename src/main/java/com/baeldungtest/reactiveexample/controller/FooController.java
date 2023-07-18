package com.baeldungtest.reactiveexample.controller;

import com.baeldungtest.reactiveexample.model.Foo;
import com.baeldungtest.reactiveexample.service.FooService;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping(path = {"/foo"})
public class FooController {

  private final int streamSeconds;
  private final FooService fooService;

  @Autowired
  public FooController(@Value("${foo.stream.seconds:1}") int streamSeconds, FooService fooService) {
    this.streamSeconds = streamSeconds;
    this.fooService = fooService;
  }

  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Foo> streamAllFoos() {
    return Flux.zip(Flux.interval(Duration.ofSeconds(streamSeconds)), fooService.getAllFoos()).map(Tuple2::getT2);
  }

}
