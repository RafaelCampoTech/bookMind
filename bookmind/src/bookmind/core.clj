(ns bookmind.core
  (:gen-class)
  (:require
   [org.httpkit.server :as http]
   [taoensso.timbre :as t]
   [bookmind.handler :refer [handler]]))

(defonce server (atom nil))

(defn stop-server []
  (t/info "Stoping the server")
  (when-let [stop-fn @server]
    (stop-fn :timeout 100)
    (reset! server nil)))


(defonce server-settings
  {:port  8080})


(defn start-server
  []
  (t/info "Starting the server in port")
  (reset! server
          (http/run-server #'handler server-settings)))

(defn reset-server!
  []
  (stop-server)
  (start-server))


(defn -main [& _]
  (t/info "Main Thread")
  (start-server)
  (println "Listening on http://localhost:8080/docs"))