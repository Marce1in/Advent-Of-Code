(ns day2-2
  (:require [clojure.string :refer [split-lines]]))

(declare
 string->map
 split-num-key
 str-to-num)

(def s (slurp "input"))

(defn -main []
  (->> (split-lines s)
       (map #(re-seq #"\d+ ." %))
       (map #(vals (string->map %)))
       (map #(reduce * %))
       (reduce +)))

(defn string->map [coll]
  (loop [col coll base (hash-map :r 0 :g 0 :b 0)]
    (if (nil? (first col))
      base
      (recur (drop 1 col)
             (merge-with max
                         (split-num-key (first col))
                         base)))))

(defn split-num-key [s]
  (hash-map (keyword (str (last s))) (str-to-num s)))

(defn str-to-num [s]
  (Integer. (re-find #"\d+" s)))

(-main)
