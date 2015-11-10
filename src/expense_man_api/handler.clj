(ns expense-man-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]))

(defroutes app-routes
  (GET "/" request
       (let [name (or (get-in request [:params :name])
                      (get-in request [:body :name])
                       "Deepankar" )]
           {:status 200
            :body {:name name
            :desc (str "The name you sent" name)}}))
  (route/resources "/")
  (route/not-found "Not found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)))
