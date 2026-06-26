(ns bookmind.core
  (:gen-class)
  (:require
   [org.httpkit.server :as http]
   [bookmind.handler :refer [handler]])) 

(defonce server (atom nil))

(defn stop-server []
  (when-let [stop-fn @server]
    (stop-fn :timeout 100)
    (reset! server nil)))

(defn -main [& _]
  (reset! server
          (http/run-server #'handler {:port 8080}))
  (println "Listening on http://localhost:8080/docs"))