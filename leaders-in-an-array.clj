(defn solve
  [input]
  (loop [leaders [] elements input]
    (cond
      (= (count elements) 0) leaders
      (= (apply max elements) (first elements)) (recur (conj leaders (first elements)) (rest elements))
      :else (recur leaders (rest elements)))))

(defn create-input
  [len]
  (vec (repeatedly len #(rand-int 1001))))
