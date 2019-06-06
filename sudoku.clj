(defn solve
  [board]
  (map check (partition 9 (flatten (vector
  (partition 9 (vec (apply concat (apply map vector (take 3 (partition 9 board))))))
  (partition 9 (vec (apply concat (apply map vector (partition 9 (subvec board 27 54))))))
  (partition 9 (vec (apply concat (apply map vector (nthrest (partition 9 board) 6)))))))))) ; squares
;  (map check (apply map vector (partition 9 board)))) ; columns
;  (map check (partition 9 board))) ; rows

(defn tester
  [board]
  (apply list (subvec board 27 54)))


(defn check
  [block]
  (clojure.set/subset? #{1 2 3 4 5 6 7 8 9} (set block)))
