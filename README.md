# clojure-api-otel

## tl;dr

Following steps from clj-otel's [tutorial](https://github.com/steffan-westcott/clj-otel/blob/master/doc/tutorial.adoc).

## Step by step

### 1. Run Jaeger
```bash
docker run --rm -p 16686:16686 -p 14250:14250 jaegertracing/all-in-one
```

### 2. Start REPL (using Lein)

Note that Open Telemetry jar has already been downloaded, as of 2022-September.

You must use Lein as that's how dependencies and JVM optionals (-javaagent:opentelemetry-javaagent.jar) were configured.

In `src/clojure_api_otel/counter_service.clj` run the `(comment (do ...))` to send a few requests to the server api.

### 3. Check in Jaeger the traces of your requests

Access `http://localhost:16686/search` and search for service `counter-service`.

There you can interact with traced requests.

It should work.