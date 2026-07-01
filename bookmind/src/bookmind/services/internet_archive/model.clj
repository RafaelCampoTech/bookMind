(ns bookmind.services.internet-archive.model
  (:require [environ.core :refer [env]]
            [org.httpkit.client :as http]
            [bookmind.utils]
            [ring.util.http-response :as resp]))


(defonce base-url   "https://archive.org/advancedsearch.php")


(defn search-archive [query]
  (let [search-params (-> (str "?q=" query "&fl[]=identifier,title,description&rows=10&page=1&output=json")
                          bookmind.utils/encode-url)
        url  (str base-url search-params)]
    (http/get url)))


(defn get-book
  [{{body :body} :parameters}]
  {:status 200
   :body body})


(comment
  (search-archive "quantum computing")
  "https://archive.org/advancedsearch.php?q=quantum+computing&fl[]=identifier,title,description&rows=10&page=1&output=json")

