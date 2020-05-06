(defproject shortener "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main shortener.handler
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-core "1.7.0"]
                 [ring/ring-jetty-adapter "1.7.0"]
                 [com.novemberain/monger "3.5.0"]
                 [ring-cors "0.1.13"]
                 [ring-json-response "0.2.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler shortener.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
