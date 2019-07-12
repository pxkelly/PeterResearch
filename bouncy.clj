(defn solve
  [in]
  (count (filter true? (map #(> in %) bouncy-number-seq))))

; Given a number, determine if it is bouncy
(defn bounce
  [num]
  (let [digits (map #(Character/digit % 10) (str num))]
  (if (or
        (apply <= digits)
        (apply >= digits)) false true)))

(def bouncy-number-seq
  (filter bounce (range 50000)))
