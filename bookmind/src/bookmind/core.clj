(ns bookmind.core
  (:gen-class)
  (:require
   [org.httpkit.server :as http]
   [taoensso.timbre :as t]
   [bookmind.handler :refer [handler]]))

(defonce server (atom nil))

(defonce server-settings
  {:port  8080})


(defn -kill-port! [port]
  (let [cmd (format "lsof -ti tcp:%s | xargs kill -9" port)]
    (.waitFor (.exec (Runtime/getRuntime) cmd))))


(defn stop-server []
  (t/info "Stoping the server")
  (when-let [stop-fn @server]
    (stop-fn :timeout 100)
    (-kill-port! (:port server-settings))
    (reset! server nil)))


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
  (println (str "Listening on http://localhost:" (:port server-settings) "/docs")))
