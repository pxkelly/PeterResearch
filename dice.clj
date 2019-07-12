(defn solve
  [in1 in2]
  (let [all-rolls (combo/cartesian-product (range 1 (inc in1)) (range 1 (inc in2)))]
    (float (/ (count (filter true? (map #(> (first %) (second %)) all-rolls)))
              (count all-rolls)))))


(defn input
  []
  (let [die1 (rand-int 100)
        die2 (rand-int 100)]
      (cond
        (> die1 die2) [die2 die1]
        (< die1 die2) [die1 die2]
        :else [die1 (inc die2)])))
