(ns bookmind.services.internet-archive.router
  (:require
   [bookmind.services.internet-archive.handler :as handler]))


(defn routes []
  ["/archive"
   {:get {:summary "Search books in the Internet Archive"
          :description "Search books by title with pagination."
          :tags ["Internet Archive"]

          :parameters
          {:query
           {:title string?
            :limit {:optional true
                    :default 20
                    :schema int?}
            :offset {:optional true
                     :default 0
                     :schema int?}}}

          :responses
          {200 {:body
                {:num-found int?
                 :start int?
                 :docs vector?}}}

          :handler handler/get-book-handler}}])