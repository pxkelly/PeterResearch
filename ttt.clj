(defn ttt [in]
  (case
      (let [rows [[(nth in 0) (nth in 1) (nth in 2)]
                  [(nth in 3) (nth in 4) (nth in 5)]
                  [(nth in 6) (nth in 7) (nth in 8)]]
            cols (apply map vector rows)
            diags (map #(map % (range 3)) [#((rows %) %) #((rows %) (- 2 %))])
            lines (concat rows cols diags)]
      (first (some (comp #{#{"X"} #{"O"}} set) lines)))
      "X" "X won"
      "O" "O won"
      nil "No winner"))
