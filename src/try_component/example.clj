(ns try-component.example
  (:require [com.stuartsierra.component :as component]))

(defrecord Example []
  component/Lifecycle

  (start [this]
    (println "start :: database : " (-> this :db :conn) ", api : " (-> this :api :session))
    this)

  (stop [this]
    (println "stop :: database : " (-> this :db :conn) ", api: " (-> this :api :session))
    this))

(defn example-component []
  (map->Example {}))
