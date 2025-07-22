(ns day2-1
  (:require [clojure.string :refer [split-lines]]))

(declare
 str-to-num
 split-num-key
 parse-string
 evaluator)

(def s (slurp "input"))
(def max-per-color {:r 12, :g 13, :b 14})

(defn -main []
  (->> (split-lines s)
       (map #(re-seq #"\d+ .|\d+" %))
       (map #(map-indexed parse-string %))
       (map #(if (some evaluator (rest %)) 0 (first %)))
       (reduce +)))

(defn parse-string [i f]
  (if (zero? i) (str-to-num f) (split-num-key f)))

(defn split-num-key [s]
  (hash-map (keyword (str (last s))) (str-to-num s)))

(defn str-to-num [s]
  (Integer. (re-find #"\d+" s)))

(defn evaluator [coll]
  (let [k (first (keys coll))]
    (if (> (get coll k) (get max-per-color k))
      true
      false)))

(-main)
