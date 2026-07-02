(ns bookmind.services.internet-archive.model
  (:require [org.httpkit.client :as http]
            [bookmind.utils :as utils]))


(defonce base-url "https://archive.org/advancedsearch.php")


(defn fetch-books-by-title
  "Queries Internet Archive API for a given title and returns the results as a map containing the status, body, and headers of the response."
  [title & {:keys [rows page]}]
  (let [url (str base-url)
        options {:query-params {"q" title
                                "fl[]" ["identifier" "title" "description"]
                                "rows" (or rows 10)
                                "page" (or page 1)
                                "output" "json"}}]
    (-> @(http/get url options)
        (select-keys [:status :body :headers])
        utils/parse-body-params)))


(defn search-book-by-title
  "Searches for books in the Internet Archive by title and returns a map containing the status and response body."
  [title]
  (let [{:keys [status body]} (fetch-books-by-title title)
        response (get body :response)]
    (cond (not= status 200)
          (throw (ex-info "Error fetching books from Internet Archive" {:status status :body body}))

          :else  {:status status
                  :response response})))