(ns try-component.api
  (:require [com.stuartsierra.component :as component]))

(defrecord API [session host]
  component/Lifecycle

  (start [this]
    (println "Start API component.")
    (assoc this :session (format "using api: host=%s>" host)))

  (stop [this]
    (println "Stop API component.")
    (assoc this :session nil)))

(defn api-component [host]
  (map->API {:host host}))
