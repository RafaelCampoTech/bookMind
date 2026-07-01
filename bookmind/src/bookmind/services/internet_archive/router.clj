(ns bookmind.services.internet-archive.router
  (:require [bookmind.services.internet-archive.model :as model]
            [bookmind.schemas :refer [User]]))


(defn routes []
  ["/archive"
   {:get {:summary "Get a file from internet archive"
          :description "Creates a user"
          :tags ["Internet Archive"]
          :parameters {:body User}
          :responses {200 {:body User}}
          :handler model/get-book}}])