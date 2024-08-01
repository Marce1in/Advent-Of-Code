(declare
  split
  get_first_digit
  get_last_digit
  str_to_num find_first
  digit?
  between?
  pow
)

(def CALIBRATION_DOCUMENT (slurp "input"))

(defn -main []
  (as-> CALIBRATION_DOCUMENT v
    (split v \newline)
    (map #(str (get_first_digit %1) (get_last_digit %1)) v)
    (map str_to_num v)
    (reduce + v)
  )
)

(defn split [string character]
  (->> string
    (partition-by #(= character %1))
    (take-nth 2)
  )
)

(defn get_first_digit [string]
  (find_first digit? string)
)

(defn get_last_digit [string]
  (find_first digit? (reverse string))
)

(defn find_first [func arr]
  (first (filter func arr))
)

(defn digit? [digit]
  (if (between? 48 57 (int digit))
    true
    false
  )
)

(defn between? [min max n]
  (if (>= n min)
    (if (<= n max)
      true
      false
    )
    false
  )
)

(defn str_to_num [string]
  (->> string
    (map int)
    (map #(- %1 48))
    reverse
    (map-indexed
      #(
        if (not (zero? %1))
        (* (pow 10 %1) %2)
        %2
        )
      )
    (reduce +)
  )
)

(defn pow
  ([x y]
    (if (= y 1)
      x
      (pow (* x x) (dec y) x)
    )
  )
  ([x y z]
    (if (= y 1)
      x
      (pow (* x z) (dec y) x)
    )
  )
)


(-main)
