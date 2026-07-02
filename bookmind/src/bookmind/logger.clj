(ns bookmind.logger
  (:require [taoensso.timbre :as t]))


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


(comment
  (t/with-context {:request-id "123"}
    (t/info "Starting"))) 