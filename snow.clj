(defn solve
  [hours start acc melt]
  (loop [time hours total start]
    (if (= time 0) total
                   (recur (dec time) (+ (* total (- 1 melt)) acc)))))

(defn input
  []
  (vector (inc (rand-int 20)) (rand 20) (rand 10) (rand)))
