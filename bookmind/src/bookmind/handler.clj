(ns bookmind.handler
  (:require
   [bookmind.routes :refer [router]] 
   [reitit.ring :as ring]
   [reitit.swagger-ui :as swagger-ui]
   [reitit.coercion.schema]))


(def handler
  (-> (ring/ring-handler
       (router)
       (swagger-ui/create-swagger-ui-handler
        {:path "/docs"
         :url "/openapi.json"}))))