(defproject bookmind "0.1.0-SNAPSHOT"
  :description "Process the bookmind brain"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [http-kit "2.3.0"]
                 [metosin/muuntaja "0.6.11"]
                 [environ "1.2.0"]
                 [cheshire "6.2.0"]  
                 [metosin/reitit "0.10.1"]
                 [metosin/ring-http-response "0.9.5"]
                 [fi.metosin/reitit-openapi "0.10.1"]
                 [metosin/reitit-swagger-ui "0.10.1"]
                 [metosin/jsonista "0.3.13"]
                 [mount "0.1.24"]
                 [nrepl "1.7.0"]
                 [com.taoensso/timbre "6.8.0"]]
  :main ^:skip-aot bookmind.core
  :target-path "target/%s"
  :profiles {:dev {}
             :uberjar {:aot [bookmind.core]
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
