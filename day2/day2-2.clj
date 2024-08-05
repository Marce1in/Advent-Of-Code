(ns day2-2
  (:require [clojure.string :refer [split-lines]]))

(declare
 foo
 split-num-key
 str-to-num)

(def s (slurp "input"))

(defn -main []
  (->> (split-lines s)
       (map #(re-seq #"\d+ .|;" %))
       (map foo)))

(defn foo [coll]
  (loop [col coll c (hash-map :r 0, :g 0, :b 0)]
    (if (or (= (first col) ";") (= (first col) nil))
      c
      (recur (drop 1 col)
             ((merge-with max
                          (split-num-key (first col))
                          c))))))

(foo '("2 r" ";" "3 r" "4 b" "9 g"))

(defn split-num-key [s]
  (hash-map (keyword (str (last s))) (str-to-num s)))

(defn str-to-num [s]
  (Integer. (re-find #"\d+" s)))

(-main)
