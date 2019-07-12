(defn counter
  [change m n]
  (cond
    (= n 0) 1
    (< n 0) 0
    (and (<= m 0) (>= n 1)) 0
    :else (+ (counter change (dec m) n) (counter change m (- n (nth change (dec m)))))))

(defn solve
  [in1 in2]
  (counter in1 (count in1) in2))

(defn input
  []
  (let [change (vec (repeatedly (inc (rand-int 10)) #(inc (rand-int 50))))]
       (vector change (+ (apply max change) (rand-int (- 50 (apply max change)))))))
