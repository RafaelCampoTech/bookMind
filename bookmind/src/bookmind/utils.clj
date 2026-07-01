(ns bookmind.utils)

(defn encode-url [s]
  (-> (java.net.URLEncoder/encode s "UTF-8")
      (.replace "+" "%20")))