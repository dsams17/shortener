(ns shortener.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]
            [shortener.util :as util]
            [shortener.db :as db]
            [ring.util.json-response :refer [json-response]]
            [ring.middleware.cors :refer [wrap-cors]]))

(defroutes app-routes
  (GET "/db/delete" [] (db/delete-all))
  (GET "/db/all" [] (db/get-all))
  (GET "/:url" [url]
    (json-response (util/retrieve-url url)))
  
  (GET "/shorten6/:url{https?:%2F%2F(www\\.)?[-a-zA-Z0-9@:%_\\+~#=]{1,256}.[a-zA-Z0-9()]{1,6}([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)}" [url]
                        (json-response (util/shorten url 6)))
  (GET "/shorten8/:url{https?:%2F%2F(www\\.)?[-a-zA-Z0-9@:%_\\+~#=]{1,256}.[a-zA-Z0-9()]{1,6}([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)}" [url] 
                        (json-response (util/shorten url 8)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (wrap-cors :access-control-allow-origin [#".*"]
                      :access-control-allow-methods [:get :put :post :delete])))

(defn -main
  []
  (run-jetty app {:port 3000}))

