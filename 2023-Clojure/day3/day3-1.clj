(ns day3-1
  (:require [clojure.string :refer [split-lines]]))

(declare
 filter-chars-with-index
 map-matrix-indexed
 digit?
 between?
 agroup-neighbors-indexes
 idx-are-neighbors?
 filter-digits-neighbors-to-symbols
 symbol-adjacent?)

(def s (slurp "input"))

(defn -main []
  (let [char-matrix (mapv vec (split-lines s))
        symbols (filter-chars-with-index
                 #(and (not (digit? %)) (not (= % \.)))
                 char-matrix)]
    (->> char-matrix
         (filter-chars-with-index digit?)
         (agroup-neighbors-indexes)
         (filter-digits-neighbors-to-symbols symbols)
         (map #(apply str (map last %)))
         (map #(Integer. %))
         (reduce +))))

(defn filter-chars-with-index [f coll]
  (let [digits (atom [])]
    (map-matrix-indexed
     #(when (f %3) (swap! digits conj [[%1 %2] %3])) coll)
    @digits))

(defn map-matrix-indexed [f coll]
  (loop [f f row 0 col 0 coll coll]
    (if (nil? (get-in coll [row col]))
      coll
      (let [col-end? (>= (inc col) (count (get coll row)))
            next-row (if (true? col-end?) (inc row) row)
            next-col (if (true? col-end?) 0 (inc col))]
        (recur f
               next-row
               next-col
               (assoc-in coll [row col] (f row col (get-in coll [row col]))))))))

(defn digit? [digit]
  (between? 48 (int digit) 57))

(defn between? [min n max]
  (and (>= n min) (<= n max)))

(defn agroup-neighbors-indexes [coll]
  (let [prev-elm (atom nil) digits-group-count (atom 0)]
    (partition-by #(if (or (nil? @prev-elm) (idx-are-neighbors? @prev-elm %))
                     (do
                       (reset! prev-elm %)
                       @digits-group-count)
                     (do
                       (reset! prev-elm %)
                       (swap! digits-group-count inc)
                       @digits-group-count)) coll)))

(defn idx-are-neighbors? [elm next-elm]
  (= (last (first elm))
     (dec (last (first next-elm)))))

(defn filter-digits-neighbors-to-symbols [symbols digits]
  (filter #(symbol-adjacent? symbols %) digits))

(defn symbol-adjacent? [symbols digits-group]
  (some (fn [symbol]
          (when (between?
                 (dec (ffirst symbol))
                 (first (ffirst digits-group))
                 (inc (ffirst symbol)))
            (some
             (fn [digit]
               (between?
                (dec (last (first symbol)))
                (last (first digit))
                (inc (last (first symbol)))))
             digits-group)))
        symbols))

(-main)
