(defn solve
  [height bounce window]
  (if (<= height window) -1
    (loop [h height times 0]
      (cond
        (<= h window) (inc times)
        :else (recur (* h bounce) (inc times))))))

(defn input
  []
  (list (inc (rand-int 100)) (rand) (inc (rand-int 50))))
