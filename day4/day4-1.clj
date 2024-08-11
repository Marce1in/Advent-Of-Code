(ns day4-1
  (:require [clojure.string :refer [split-lines]]
            [clojure.set :refer [intersection]]
            [clojure.math :refer [pow]]))

(def s (slurp "input"))

(defn -main []
  (->> (split-lines s)
       (map #(rest (re-seq #"\d+|\|" %)))
       (map #(partition-by #{"|"} %))
       (map #(remove #{'("|")} %))
       (map #(map set %))
       (map #(apply intersection %))
       (map count)
       (map #(if (> % 2) (pow 2 (- % 1)) %))
       (reduce +)))

(-main)
