(ns try-component.database
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [conn host port]
  component/Lifecycle

  (start [this]
    (println "Start DB component.")
    (assoc this :conn (format "Using db://%s:%d>" host port)))

  (stop [this]
    (println "Stop DB component.")
    (assoc this :conn nil)))

(defn new-database [host port]
  (map->Database {:host host :port port}))
