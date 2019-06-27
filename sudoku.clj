(defn rotate
  [n coll]
  (let [c (count coll)]
    (take c (drop (mod n c) (cycle coll)))))

(defn create
  []
  (let [row (rand-nth (combo/permutations [1 2 3 4 5 6 7 8 9]))]
    (vector row
            (vec (rotate -3 row))
            (vec (rotate -6 row))
            (vec (rotate -7 row))
            (vec (rotate -10 row))
            (vec (rotate -13 row))
            (vec (rotate -14 row))
            (vec (rotate -17 row))
            (vec (rotate -20 row)))))

(defn check
  [block]
  (clojure.set/subset? #{1 2 3 4 5 6 7 8 9} (set block)))

(defn square-divide
  [board]
  (loop [i 0 squares []]
    (if-not (< i 9)
      squares
      (recur (+ i 3)
             (into squares
                   (partition 9 (apply concat (map #(take 3 (drop i %)) board))))))))
(defn solver
  [board]
  (if (and
        (every? true? (map check board))
        (every? true? (map check (apply map vector board)))
        (every? true? (map check (square-divide board))))
        true false))

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
