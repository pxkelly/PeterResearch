(defn solve
  [input]
  (println input)
  (count (filter #(not= % \0) (Integer/toString input 2))))


(defn input
  []
  (rand-int 1000000))
