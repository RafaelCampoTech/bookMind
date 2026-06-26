(ns bookmind.routes
  (:require
   [reitit.ring :as ring]
   [ring.middleware.params :as params]
   [muuntaja.core :as m]
   [muuntaja.middleware :as muuntaja]
   [reitit.openapi :as openapi]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.schema]
   [schema.core :as s]))

(s/defschema Address
  {:street s/Str
   :city (s/enum :tre :hki)})

(s/defschema User
  {:id s/Str
   :name s/Str
   :address Address})

(def routes
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
                        :body body})}}]])

(def router
  (ring/router
   routes
   {:data {:muuntaja m/instance
           :middleware [params/wrap-params
                        muuntaja/wrap-format
                        coercion/coerce-exceptions-middleware
                        coercion/coerce-request-middleware
                        coercion/coerce-response-middleware]}}))