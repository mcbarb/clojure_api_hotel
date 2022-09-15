(ns clojure-api-otel.core
  (:require [clojure-api-otel.counter-service]) ;; so it automatically loads when running REPL
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(comment
  (-main)
  (:ns (meta #'-main))

  (defn ^:debug ^{:other "other info about hey"} hey
    [name]
    (println (str "Hey" name)))
  
  (meta #'hey)

  (def five 5)
  `five
  #'five

  *file*
  inc
  -main
  (ns-interns *ns*)

  (def three-and-four (list 3 4))
  `three-and-four
  `(1 three-and-four)
  `(1 ~three-and-four)
  `(1 ~@three-and-four)
  )
