(defn solve
  [in]
  (println (/ (reduce + in) (count in)))
  (vec (filter #(> % (/ (reduce + in) (count in))) in)))

(defn input
  [len]
  (vec (repeatedly len #(rand 10000))))
