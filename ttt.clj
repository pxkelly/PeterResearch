(defn ttt [in]
  (let [rows [(nth in 0)
              (nth in 1)
              (nth in 2)]
        cols (apply map vector rows)
        diags (map #(map % (range 3)) [#((rows %) %) #((rows %) (- 2 %))])
        lines (concat rows cols diags)]
        (cond
          (apply = (first lines)) [0 0]   ; solution starts at position 0,0
          (apply = (second lines)) [1 0]  ; starts at 1,0
          (apply = (nth lines 2)) [2 0]   ; starts at 2,0
          (apply = (nth lines 3)) [0 0]   ; starts at 0,0
          (apply = (nth lines 4)) [0 1]   ; starts at 0,1
          (apply = (nth lines 5)) [0 2]   ; starts at 0,2
          (apply = (nth lines 6)) [0 1]  ; starts at 0,1
          (apply = (nth lines 7)) [0 2]  ; starts at 0,2
          :else [-1 -1]))) ; no answer

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
