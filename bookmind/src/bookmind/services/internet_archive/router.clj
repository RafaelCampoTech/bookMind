(ns bookmind.services.internet-archive.router
  (:require
   [schema.core :as s]
   [bookmind.services.internet-archive.handler :as handler]))

(s/defschema ArchiveQuery
  {:title s/Str
   (s/optional-key :limit) s/Int
   (s/optional-key :offset) s/Int})

(s/defschema ArchiveResponse 
  {:num-found s/Int
   :start s/Int
   :docs [s/Any]})

(defn routes []
  ["/archive"
   {:get {:summary "Search books in the Internet Archive"
          :description "Search books by title with pagination."
          :tags ["Internet Archive"]

          :parameters
          {:query ArchiveQuery}

          :responses
          {200 {:body s/Any}}

          :handler handler/get-book-handler}}])
