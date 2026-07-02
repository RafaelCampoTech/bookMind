(ns bookmind.middleware
  (:require [bookmind.middleware.logger :refer [wrap-logging-context*]]))



 (defn wrap-content-type [handler content-type]
   (fn [request]
     (let [response (handler request)]
       (assoc-in response [:headers "Content-Type"] content-type))))
 

 (defn middleware-chain 
   [handler] 
   (-> handler
       (wrap-logging-context*)
       (wrap-content-type "application/json")))