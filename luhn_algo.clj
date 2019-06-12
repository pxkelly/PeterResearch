(defn solve
  [in]
  (let [digits (map #(Character/digit % 10) (str in))
        split (if (even? (count digits)) (apply map list (partition 2 digits))
                                         (apply map list (partition 2 (rest digits))))]
    (if (= (mod
            (reduce +
              (flatten (conj (map #(if (> % 9) (- % 9) %)
                              (map #(* 2 %)
                                (nth split 0)))
                             (if (even? (count digits)) (nth split 1)
                                                        (conj (nth split 1) (nth digits 0))))))
            10) 0) true false)))

(defn input
  []
  (+ (rand-int 99990) 10))
