(defn solve
  [in]
  (loop [decimal 0.13 binary ""]
  (println (round-to-n-decimal-places decimal 3))
    (cond
      (= decimal 0.0) binary
      (>= (* 2 decimal) 1.0) (recur (dec (* 2 (Math/round decimal))) (str binary "1"))
      :else (recur (* (Math/round decimal 2)) (str binary "0")))))

  ;(Integer/toString in 2)) ; gets the non-decimal
