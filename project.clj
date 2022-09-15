(defproject clojure-api-otel "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojure "1.11.1"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [clj-http "2.3.0"]]
  :main ^:skip-aot clojure-api-otel.core
  :target-path "target/%s"
  :jvm-opts ["-javaagent:opentelemetry-javaagent.jar"
             "-Dotel.resource.attributes=service.name=counter-service"
             "-Dotel.traces.exporter=jaeger"
             "-Dotel.metrics.exporter=none"]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
