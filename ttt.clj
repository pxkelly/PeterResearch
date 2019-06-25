(defn ttt [in]
  (println (nth in 0))
  (case
      (let [rows [(nth in 0)
                  (nth in 1)
                  (nth in 2)]
            cols (apply map vector rows)
            diags (map #(map % (range 3)) [#((rows %) %) #((rows %) (- 2 %))])
            lines (concat rows cols diags)]
      (first (some (comp #{#{"X"} #{"O"}} set) lines)))
      "X" "X won"
      "O" "O won"
      "No winner"))

;; Define test cases
(defn tic-tac-toe-input
  "Makes a Tic Tac Toe input."
  [in]
    (case in
              0 "."
              1 "O"
              2 "X"))


(defn make-row
  []
  (vec (repeatedly 3 #(tic-tac-toe-input (rand-int 3)))))
