(defn make-frames [rolls]
  (let [must-take (if (or (= 10 (reduce + (take 2 rolls))) (= 10 (first rolls))) 3 2)
        must-drop (if (= 10 (first rolls)) 1 2)]
    (lazy-seq
     (cons
      (take must-take rolls)
      (make-frames (drop must-drop rolls))))))

(defn game [rolls]
  (reduce + (map #(reduce + %) (take 10 (make-frames rolls)))))


(defn bowling-input
  "Makes a bowling input."
  []
  (loop [frames 0 game []]
    (cond
      (>= frames 10) game   ; The game is generated, so return that
      (= frames 9) (let [score (rand-int 11)   ; The last frame is very special
                         score2 (rand-int (- 11 score))
                         score3 (rand-int 11)
                         score4 (rand-int 11)]
                         (cond
                            (= score 10) (recur (inc frames) (conj game score score3 score4))  ; Strike gets 2 more bowls
                            (= (+ score score2) 10) (recur (inc frames) (conj game score score2 score3)) ; Spare gets 1 more bowl
                            :else (recur (inc frames) (conj game score score2))))  ; Otherwise, just 2 normal bowls
      :else (let [score (rand-int 11)    ; otherwise, generate 1 or two numbers
            score2 (rand-int (- 11 score))]
           (if (= score 10)
               (recur (inc frames) (conj game score))
               (recur (inc frames) (conj game score score2)))))))
