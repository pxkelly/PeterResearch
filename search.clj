; (defn solve
;   [word puzzle]
;   (loop [current "" letter 0 pos 0 startpos 0 samepos false direction ""]
;     (cond
;       (= current word) true ; if the word is found, return true
;       (and
;            (= startpos (dec (* row col)))
;            (not= current word)) false   ; if the word isn't in the puzzle, return false
;       (and (= samepos false)
;            (= (nth word letter) (nth puzzle pos))) (recur (str current (nth puzzle pos)) (inc letter) pos startpos true direction) ; if the current letter is correct
;       (and (not= (mod (inc pos) col) 0)  ; if the next letter to the right is correct and it isn't out of bounds
;            (= (nth word letter) (nth puzzle (inc pos)))
;            (or (= direction "") (= direction "right"))) (recur current letter (inc pos) startpos false "right")
;       (and (< (+ pos col) (* row col))  ; if the next letter is down and it isn't out of bounds
;            (= (nth word letter) (nth puzzle (+ pos col)))
;            (or (= direction "") (= direction "down"))) (recur current letter (+ pos col) startpos false "down")
;       :else (recur "" 0 (inc startpos) (inc startpos) false ""))))

(defn solve
  [word puzzle]
  (loop [current "" letter 0 row 0 col 0 direction "" start-row 0 same-letter false]
    (cond
      (= (apply str current) word) true   ; word has been found
      (and (= same-letter false)
           (= (str (nth word letter)) (str (nth (nth puzzle row) col)))) (recur (concat current (nth (nth puzzle row) col)) (inc letter) row col direction start-row true)
      (and (= row (dec (count puzzle)))
           (= col (dec (count (first puzzle))))) false   ; word was not found
      (and (= (str (nth word letter)) (str (nth (nth puzzle row) (inc col) nil))) ; letter to the right
           (or (= direction "") (= direction "right"))) (recur current letter row (inc col) "right" start-row false)
      (and (= (str (nth word letter)) (str (nth (nth puzzle (inc row) nil) col))) ; letter down
           (or (= direction "") (= direction "down"))) (recur current letter (inc row) col "down" start-row false)
      (= col (dec (count (first puzzle)))) (recur "" 0 (inc start-row) 0 "" (inc start-row) false) ; no letter, advance row, reset col
      :else (recur "" 0 start-row (inc col) "" start-row false)))) ; no letter, advance col

(defn create
  [row col]
  (apply str (repeatedly (* row col) #(char (+ 97 (rand-int 26))))))

; Helper function 1 for input
(defn puzzle-word-creation
  [row col direction puzzle]
  (cond
    (= direction 0) (clojure.string/join (rand-nth puzzle))
    :else (clojure.string/join (map #(nth % (rand-int col)) puzzle))))

; Helper function 2 for input
(defn make-word
  [row col puzzle use-puzzle]
  (if (= use-puzzle 1)
      (puzzle-word-creation row col (rand-int 2) puzzle)
      (apply str (repeatedly row #(char (+ 97 (rand-int 26)))))))

(defn make-row
  [len]
  (vec (map str (repeatedly len #(char (+ 97 (rand-int 26)))))))

;; Define test cases
(defn word-search-input
  "Makes a word search input of size row col."
  [row col]
  (let [puzzle (vec (repeatedly row #(make-row col)))
        word (make-word row col puzzle (rand-int 2))]
        (vector word puzzle)))
