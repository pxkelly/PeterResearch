(defn solve
  [input]
  (format "%h" input))

(defn input
  []
  (rand-int 500000))

(defn counter
  [in]
  (reduce + (map #(if (clojure.string/includes? "0123456789abcdef" (str (first %))) 0 (second %)) (frequencies in))))
