(defn solve
  [board]
  (if (not= (count board) 81) false
                              (if (and (every? true?
                                          (map check (partition 9 (flatten (vector
                                                     (partition 9 (vec (apply concat (apply map vector (take 3 (partition 9 board))))))
                                                     (partition 9 (vec (apply concat (apply map vector (partition 9 (subvec board 27 54))))))
                                                     (partition 9 (vec (apply concat (apply map vector (nthrest (partition 9 board) 6)))))))))) ; squares
                                        (every? true? (map check (apply map vector (partition 9 board)))) ; columns
                                        (every? true? (map check (partition 9 board)))) true false))) ; rows

(defn tester
  [board]
  (map check (partition 9 (flatten (vector
                         (partition 9 (vec (apply concat (apply map vector (take 3 (partition 9 board))))))
                         (partition 9 (vec (apply concat (apply map vector (partition 9 (subvec board 27 54))))))
                         (partition 9 (vec (apply concat (apply map vector (nthrest (partition 9 board) 6))))))))))


(defn check
  [block]
  (clojure.set/subset? #{1 2 3 4 5 6 7 8 9} (set block)))


  (defn sudoku-test-cases
    "Takes a sequence of inputs and gives IO test cases of the form
     [input output]."
    [inputs]
    (map (fn [in]
           (vector in
             (if (not= (count in) 81)
                 false
                 (if (and (every? true?
                                (map check (partition 9 (flatten (vector
                                           (partition 9 (vec (apply concat (apply map vector (take 3 (partition 9 in))))))
                                           (partition 9 (vec (apply concat (apply map vector (partition 9 (subvec in 27 54))))))
                                           (partition 9 (vec (apply concat (apply map vector (nthrest (partition 9 in) 6)))))))))) ; squares
                          (every? true? (map check (apply map vector (partition 9 in)))) ; columns
                          (every? true? (map check (partition 9 in)))) true false))))
         inputs))
