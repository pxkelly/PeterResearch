(defn solve
  [in1 in2]
  (loop [n in1 k in2 steps 0]
    (cond
      (= n 0) steps
      (= (mod n k) 0) (recur (/ n k) k (inc steps))
      :else (recur (dec n) k (inc steps)))))

(defn input
  (inc (rand-int 10000)) (+ (rand-int 10000) 2))
