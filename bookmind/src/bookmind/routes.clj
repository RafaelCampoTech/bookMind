(ns bookmind.routes
  (:require
   [reitit.ring :as ring]
   [ring.middleware.params :as params]
   [muuntaja.core :as m]
   [muuntaja.middleware :as muuntaja]
   [reitit.openapi :as openapi]
   [bookmind.middleware :refer [middleware-chain]]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.schema :as schema-coercion]
   [bookmind.services.internet-archive.router :as int-arc]))


(defn routes 
  "Global routes of the project."
  [] 
  [["/openapi.json"
    {:get {:no-doc true
           :handler (openapi/create-openapi-handler)}}]

   ["/api"
    {:post {:summary "ping"
            :description "Pings the server"
            :handler (fn [_]
                       {:status 200
                        :body {:message "pong"}})}}]
   (int-arc/routes)])


(defn router []
  (ring/router
   (routes)
   {:data {:coercion schema-coercion/coercion
           :muuntaja m/instance
           :middleware [params/wrap-params
                        middleware-chain
                        muuntaja/wrap-format
                        coercion/coerce-exceptions-middleware
                        coercion/coerce-request-middleware
                        coercion/coerce-response-middleware]}}))
