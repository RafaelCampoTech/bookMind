(ns bookmind.middleware
  (:require [bookmind.logger :refer [with-log-context]]
            [taoensso.timbre :as t]
            [ring.middleware.params :as rmp]
            [ring.util.http-response :as resp]
            [ring.middleware.keyword-params :as rmkp]))



(defn do-work [& _args]
  (t/info "no context")
  (with-log-context {:request-id "123456"}
    (t/info "starting work on a new request")
    (with-log-context {:job-id "job-foobared"}
      (t/info "doing work on job"))
    (t/info "finished work on request")) )

(do-work)

(defn multi-threaded []
  (with-log-context {:request-id "123456"}
    (pmap #(with-log-context {:thread-id (.getId (Thread/currentThread))}
             (t/info "doing work in a thread")
             %)
          (range 3))))

(multi-threaded)
 


(defn handler-that-needs-keyword-params
  [{params :params}]
  (ring.util.response/response "Hello" (:name params) " your ID is: " (:id params)))

(rmp/wrap-params
 (rmkp/wrap-keyword-params handler-that-needs-keyword-params))



(comment
  (defn middleware-fn
    [handler-fn]
    (fn [request]
      ;; operations on the request:
      ;; to create a `new-request` 
      ;; or to simply log etc 
      (let [new-response (handler-fn new-request)]
        ;; operations on the `new-response`:
        ;; to create a `new-and-modified-response` 
        ;; or to simply log etc
        new-and-modified-response))))
