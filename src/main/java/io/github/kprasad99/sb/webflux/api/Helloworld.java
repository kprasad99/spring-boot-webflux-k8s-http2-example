package io.github.kprasad99.sb.webflux.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class Helloworld {

	@GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public Mono<String> sayHello() {
		return Mono.just("Hello from KP");
	}
}
