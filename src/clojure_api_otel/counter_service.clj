(ns clojure-api-otel.counter-service
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [ring.util.response :as response]))

(def PORT 8085)

(defonce counter (atom 0))

(defn wrap-exception [handler]
  (fn [request]
    (try
      (handler request)
      (catch Throwable e
        (let [resp (response/response (ex-message e))]
          (response/status resp 500))))))

(defn reset-count-handler [{:keys [query-params]}]
  (let [n (Integer/parseInt (get query-params "n"))]
    (reset! counter n)
    (response/status 204)))

(defn get-count-handler []
  (let [n @counter]
    (response/response (str n))))

(defn inc-count-handler []
  (swap! counter inc)
  (response/status 204))

(defn handler [{:keys [request-method uri] :as request}]
  (case [request-method uri]
    [:get "/"] (response/status {:body "Marcelo is working"} 200)
    [:put "/reset"] (reset-count-handler request)
    [:get "/count"] (get-count-handler)
    [:post "/inc"] (inc-count-handler)
    (response/not-found "Not found")))

(def service
  (-> handler
      params/wrap-params
      wrap-exception))

(defonce server 
  (jetty/run-jetty #'service {:port PORT :join? false}))

(comment
  (server)
  (+ 1 1)
  ) 

(comment
  'Testing
  (do
    (require '[clj-http.client :as client])
    (defn address
      ([endpoint]
       (str "http://localhost:" PORT endpoint))
      ([]
       (address "/")))

    (client/get (address))
    (client/put (address "/reset")
                {:query-params {"n" 3}})
    (client/get (address "/count") )) 
  )

