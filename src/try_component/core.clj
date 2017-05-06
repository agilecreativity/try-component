(ns try-component.core
  (:require
   [com.stuartsierra.component :as component]
   [try-component.database :as db]
   [try-component.example :as ex]
   [try-component.api :as api]))

(defn example-system [options]
  (component/system-map
    :database (db/new-database (:db-host options)
                               (:db-port options))
    :api (api/api-component (:api-host options))
    :example (component/using
               (ex/example-component)
               {:db  :database
                :api :api})))

(def system (example-system {:db-host  "db.example.com"
                             :db-port  8080
                             :api-host "api.example.com"}))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system component/stop))

(defn -main [& args]
  (println "FYI: about to start the component!")
  (start)
  (println "FYI: about to stop the component!")
  (stop))
