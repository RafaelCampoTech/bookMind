(ns bookmind.middleware.logger
  (:require [taoensso.timbre :as t])
  (:import
   [java.util UUID]))


(defonce -merge-config
  (t/merge-config!
   {:output-fn
    (fn [{:keys [level ?msg_ context]}]
      (str
       (name level)
       " "
       context
       " "
       (force ?msg_)))}))


(defn -request->context [req]
  {:request-id  (or (get-in req [:headers "x-request-id"])
                    (str (UUID/randomUUID)))
   :method      (name (:request-method req))
   :uri         (:uri req)
   :remote-addr (:remote-addr req)
   :query-params (:query-params req)
   :path-params  (:path-params req)})


(defn wrap-logging-context* [handler]
  (fn [req]
    (t/with-context (-request->context req)
      (handler req))))

