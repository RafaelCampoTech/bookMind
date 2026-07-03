(ns bookmind.middleware.logger
  (:require [taoensso.timbre :as t])
  (:import
   [java.util UUID]))


#_(defonce -merge-config
    (t/merge-config!
     {:output-fn
      (fn [data]
        (prn data)
        (force (:?msg_ data)))}))

(defonce atomic-log (atom nil))

(defn -request->context [req]
  (reset! atomic-log req)
  {:request-id  (or (get-in req [:headers "x-request-id"])
                    (str (UUID/randomUUID)))
   :method      (name (:request-method req))
   :uri         (:uri req)
   :remote-addr (:remote-addr req)
   :query-params (:query-params req)
   :path-params  (:path-params req)})


(defn wrap-logging-context* [handler]
  (fn [req]
    (t/info "API request path: "  (name (:request-method req)) (-> req
                                                                   :reitit.core/match
                                                                   :path))
    (t/with-context (-request->context req)
      (handler req))))
