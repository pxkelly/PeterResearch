(defn solve
  [word puzzle row col]
  (loop [current "" letter 0 pos 0 startpos 0 samepos false direction ""]
    (cond
      (= current word) true ; if the word is found, return true
      (and
           (= startpos (dec (* row col)))
           (not= current word)) false   ; if the word isn't in the puzzle, return false
      (and (= samepos false)
           (= (nth word letter) (nth puzzle pos))) (recur (str current (nth puzzle pos)) (inc letter) pos startpos true direction) ; if the current letter is correct
      (and (not= (mod (inc pos) col) 0)  ; if the next letter to the right is correct and it isn't out of bounds
           (= (nth word letter) (nth puzzle (inc pos)))
           (or (= direction "") (= direction "right"))) (recur current letter (inc pos) startpos false "right")
      (and (< (+ pos col) (* row col))  ; if the next letter is down and it isn't out of bounds
           (= (nth word letter) (nth puzzle (+ pos col)))
           (or (= direction "") (= direction "down"))) (recur current letter (+ pos col) startpos false "down")
      :else (recur "" 0 (inc startpos) (inc startpos) false ""))))

(defn create
  [row col]
  (apply str (repeatedly (* row col) #(char (+ 97 (rand-int 26))))))

(defn puzzle-word-creation
  [row col direction puzzle]
  (cond
    (= direction 0) (take col (drop (+ row col) puzzle))
    :else (loop [current col word ""]
                (cond
                  (< current (* row col)) (recur (+ current col) (str word (nth puzzle current)))
                  :else word))))

(defn make-word
  [puzzle use-puzzle]
  (if (= use-puzzle true)
      ))
