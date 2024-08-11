(ns day4-2
  (:require [clojure.string :refer [split-lines]]
            [clojure.set :refer [intersection]]))

(declare
 calc-score
 count-zeros
 increment-coll-by-n
 split-and-increment
 double-coll)

(def s (slurp "input"))

(defn -main []
  (->> (split-lines s)
       (map #(rest (re-seq #"\d+|\|" %)))
       (map #(partition-by #{"|"} %))
       (map #(remove #{'("|")} %))
       (map #(map set %))
       (map #(apply intersection %))
       (map count)
       (increment-coll-by-n)
       (reduce +)))

(defn increment-coll-by-n [coll]
  (let [base (take (count coll) (repeat 1))]
    (loop [coll coll
           base base
           idx 1]
      (if (empty? coll)
        base
        (recur
         (rest coll)
         (split-and-increment base (nth base (dec idx)) idx (first coll))
         (inc idx))))))

(defn split-and-increment
  ([coll n stop]
   (let
    [split-coll (split-at stop coll)
     first-part (first split-coll)
     last-part  (last split-coll)]

     (concat (map #(+ n %) first-part) last-part)))
  ([coll n start stop]
   (let
    [split-coll (split-at start coll)
     first-part (first split-coll)
     last-part  (last split-coll)]

     (concat first-part (split-and-increment last-part n stop)))))

(-main)

