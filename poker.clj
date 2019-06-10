(defn solve
  "input is a vector containing 2 strings, each of length 10.
  The first string is player 1, second is player 2.
  The format is number/letter, suit"
  [input]
  (cond
    (> (hand-rank (nth input 0)) (hand-rank (nth input 1))) "Player 1 wins"
    (< (hand-rank (nth input 0)) (hand-rank (nth input 1))) "Player 2 wins"
    :else "Tie!"))

(defn hand-rank
  "The rank is:
  8 - Straight flush
  7 - Four of a kind
  6 - Full House
  5 - Flush
  4 - Straight
  3 - Three of a kind
  2 - Two Pairs
  1 - Pair
  0 - Nothing "
  [hand-string]
  (let [hand (partition 2 hand-string)] ; creates a list of each individual card (it's a list of characters)
  (cond
    (and (= (count (remove #(not= 5 (val %)) (frequencies (list (nth (nth hand 0) 1)
                                             (nth (nth hand 1) 1)
                                             (nth (nth hand 2) 1)
                                             (nth (nth hand 3) 1)
                                             (nth (nth hand 4) 1))))) 1)
         (let [sort-hand (sort (list (nth (nth hand 0) 0)
                                     (nth (nth hand 1) 0)
                                     (nth (nth hand 2) 0)
                                     (nth (nth hand 3) 0)
                                     (nth (nth hand 4) 0)))]
                  (= (- (int (last sort-hand)) (int (first sort-hand))) 4)) true) 8   ; straight flush
    (= (count (remove #(not= 4 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                        (nth (nth hand 1) 0)
                                        (nth (nth hand 2) 0)
                                        (nth (nth hand 3) 0)
                                        (nth (nth hand 4) 0))))) 1) 7  ; 4 of a kind
    (and (= (count (remove #(not= 3 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                             (nth (nth hand 1) 0)
                                             (nth (nth hand 2) 0)
                                             (nth (nth hand 3) 0)
                                             (nth (nth hand 4) 0))))) 1)
         (= (count (remove #(not= 2 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                             (nth (nth hand 1) 0)
                                             (nth (nth hand 2) 0)
                                             (nth (nth hand 3) 0)
                                             (nth (nth hand 4) 0))))) 1)) 6   ; Full house
    (= (count (remove #(not= 5 (val %)) (frequencies (list (nth (nth hand 0) 1)
                                        (nth (nth hand 1) 1)
                                        (nth (nth hand 2) 1)
                                        (nth (nth hand 3) 1)
                                        (nth (nth hand 4) 1))))) 1) 5   ; flush
    (let [sort-hand (sort (list (nth (nth hand 0) 0)
                                (nth (nth hand 1) 0)
                                (nth (nth hand 2) 0)
                                (nth (nth hand 3) 0)
                                (nth (nth hand 4) 0)))]
             (= (- (int (last sort-hand)) (int (first sort-hand))) 4)) 4 ; straight
    (= (count (remove #(not= 3 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                        (nth (nth hand 1) 0)
                                        (nth (nth hand 2) 0)
                                        (nth (nth hand 3) 0)
                                        (nth (nth hand 4) 0))))) 1) 3 ; three of a kind
    (= (count (remove #(not= 2 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                        (nth (nth hand 1) 0)
                                        (nth (nth hand 2) 0)
                                        (nth (nth hand 3) 0)
                                        (nth (nth hand 4) 0))))) 2) 2 ; two pair
    (= (count (remove #(not= 2 (val %)) (frequencies (list (nth (nth hand 0) 0)
                                        (nth (nth hand 1) 0)
                                        (nth (nth hand 2) 0)
                                        (nth (nth hand 3) 0)
                                        (nth (nth hand 4) 0))))) 1) 1 ; one pair
    :else 0)))

(defn input
  []
  (vector (apply str (clojure.string/join (repeatedly 5 #(str (inc (rand-int 9)) (rand-nth ["h" "c" "s" "d"])))))
          (apply str (clojure.string/join (repeatedly 5 #(str (inc (rand-int 9)) (rand-nth ["h" "c" "s" "d"])))))))
