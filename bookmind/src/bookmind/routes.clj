(ns bookmind.routes
  (:require
   [reitit.ring :as ring]
   [ring.middleware.params :as params]
   [muuntaja.core :as m]
   [muuntaja.middleware :as muuntaja]
   [reitit.openapi :as openapi]
   [bookmind.middleware :refer [middleware-chain]]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.schema]
   [schema.core :as s]
   [bookmind.services.internet-archive.router :as int-arc]))

(s/defschema Address
  {:street s/Str
   :city (s/enum :tre :hki)})

(s/defschema User
  {:id s/Str
   :name s/Str
   :address Address})

(defn routes 
  "Global routes of the project."
  [] 
  [["/openapi.json"
    {:get {:no-doc true
           :handler (openapi/create-openapi-handler)}}]

   ["/api"
    {:post {:summary "Create user"
            :description "Creates a user"
            :parameters {:body User}
            :responses {200 {:body User}}
            :handler (fn [{{body :body} :parameters}]
                       {:status 200
                        :body body})}}]
   (int-arc/routes)])


(defn router []
  (ring/router
   (routes)
   {:data {:muuntaja m/instance
           :middleware [params/wrap-params
                        middleware-chain
                        muuntaja/wrap-format
                        coercion/coerce-exceptions-middleware
                        coercion/coerce-request-middleware
                        coercion/coerce-response-middleware]}}))