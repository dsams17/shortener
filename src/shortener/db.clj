(ns shortener.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import org.bson.types.ObjectId))

(def conn (mg/connect {:host "127.0.0.1" :port 27017}))

(def db (mg/get-db conn "shortener"))

(defn get-all [] (mc/find-maps db "documents"))

(defn delete-all []
  (do
    (mc/remove db "documents")
    (str "All Records in database deleted.")))


(defn find-doc [url] (mc/find-one-as-map db "documents" {:generated url}))

(defn insert-record [{generated-url :generated og-url :original}]
 (mc/insert-and-return db "documents" {:_id (ObjectId.) :generated generated-url :original og-url}))
 



