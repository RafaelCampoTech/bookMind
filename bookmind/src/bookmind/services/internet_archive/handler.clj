(ns bookmind.services.internet-archive.handler
  (:require [bookmind.services.internet-archive.model :as model]
            [ring.util.http-response :as resp]))


(defn get-book-handler
  [{{body :body} :parameters}]
  (try
    (let [title (:title body)
          response (model/search-book-by-title title)]
      (if (= (:status response) 200)
        (resp/ok (:response response))
        (resp/internal-server-error {:error "Failed to fetch book data from Internet Archive"})))

    (catch Exception e
      (resp/internal-server-error {:error (.getMessage e)
                                   :msg "An error occurred while processing the request"}))))




