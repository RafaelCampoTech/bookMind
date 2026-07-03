(ns bookmind.middleware.case
  (:require
   [camel-snake-kebab.core :as csk]
   [clojure.walk :as walk]))

(defn transform-map-keys
  "Recursively transforms all keyword map keys using the supplied function."
  [f data]
  (walk/postwalk
   (fn [x]
     (if (map? x)
       (into {}
             (map (fn [[k v]]
                    [(if (keyword? k)
                       (f k)
                       k)
                     v]))
             x)
       x))
   data))

(defn ->kebab
  "Converts all keyword map keys to kebab-case."
  [data]
  (transform-map-keys
   #(-> % name csk/->kebab-case-keyword)
   data))

(defn ->camel
  "Converts all keyword map keys to camelCase."
  [data]
  (transform-map-keys
   #(-> % name csk/->camelCaseKeyword)
   data))

(defn wrap-kebab-case-request*
  "Converts incoming Ring request keys to kebab-case."
  [handler]
  (fn [request]
    (handler
     (cond-> request
       (:query-params request)
       (update :query-params ->kebab)

       (:body-params request)
       (update :body-params ->kebab)

       (:path-params request)
       (update :path-params ->kebab)

       (:form-params request)
       (update :form-params ->kebab)

       (:multipart-params request)
       (update :multipart-params ->kebab)

       (:parameters request)
       (update :parameters ->kebab)))))

(defn wrap-camel-case-response*
  "Converts outgoing response body keys to camelCase."
  [handler]
  (fn [request]
    (let [response (handler request)]
      (if (:body response)
        (update response :body ->camel)
        response))))
 