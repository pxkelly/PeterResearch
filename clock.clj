(defn solve
  [in]
  (let [hour (Integer/parseInt (subs in 0 2)) min (Integer/parseInt (subs in 3))]
    (format "%02d:%02d"
            (if (= hour 12) 12 (- 12 hour))
            (if (= min 0) 0 (- 60 min)))))

(defn input
  []
  (format "%02d:%02d" (inc (rand-int 12)) (rand-int 60)))
