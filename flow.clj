(defn solve
  [in]
  (loop [char-pos 0 row 0 current 0 longest 0 start-row 0 start-pos 0]
    (println char-pos row current longest start-row start-pos)
    (cond
      (and (= (inc char-pos) (count (first in)))
           (= (inc row) (count in))) longest ; End of the list, return the longest river
      (= (nth (nth in row) char-pos) " ") (recur char-pos (inc row) (inc current) longest start-row start-pos) ; If the current spot is space
      (= (nth (nth in row) (inc char-pos) nil) " ") (recur (inc char-pos) (inc row) (inc current) longest start-row start-pos)  ; if the spot to the right is a space
      (= (nth (nth in row) (dec char-pos) nil) " ") (recur (dec char-pos) (inc row) (inc current) longest start-row start-pos)  ; spot to the left is a space
      (= (inc row) (count in)) (recur (inc start-pos) 0 current longest 0 (inc start-pos)) ; if nothing was found at the bottom row, then start back up top
      :else (recur 0 (inc start-row) current longest (inc start-row) 0)  ; else, we need to start a row down
      )))
