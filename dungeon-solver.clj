; Input is number of rows, num cols, vector of integers of that size

(defn create-dungeon
  [row col]
  (vec (repeatedly (* row col) #(- (rand-int 201) 100))))

(defn solve
  [dungeon row col]
  (loop [start-health 1 health-remain 1 current-spot 0]
    (cond
      (<= (+ health-remain (nth dungeon current-spot -1000)) 0) (recur (inc start-health) (inc start-health) 0) ; if the current spot kills you, then restart
      (= current-spot (dec (* row col))) (if (<= (+ health-remain (nth dungeon current-spot -1000)) 0) ; if the knight made it to the end, check if the final spot kills him
                                        (recur (inc start-health)
                                               (inc start-health)
                                               0)
                                        start-health) ; if it does, restart, otherwise, return the health
      (and (> (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot 1) -1000)) 0)  ; if the knight could survive both directions
           (> (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot col) -1000)) 0)) (if (> (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot 1) -1000))
                                                                                                                         (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot col) -1000)))
                                                                                                                  (recur   ; If going right results in having more health, go right
                                                                                                                    start-health
                                                                                                                    (+ health-remain (nth dungeon current-spot -1000))
                                                                                                                    (+ current-spot 1))
                                                                                                                  (recur  ; otherwise, go down
                                                                                                                    start-health
                                                                                                                    (+ health-remain (nth dungeon current-spot -1000))
                                                                                                                    (+ current-spot col)))
      (> (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot 1) -1000)) 0) (recur
                                                                                                            start-health
                                                                                                            (+ health-remain (nth dungeon current-spot -1000))
                                                                                                            (+ current-spot 1)) ; The knight can survive a step right, so it goes right
      (> (+ (+ health-remain (nth dungeon current-spot -1000)) (nth dungeon (+ current-spot col) -1000)) 0) (recur
                                                                                                              start-health
                                                                                                              (+ health-remain (nth dungeon current-spot -1000))
                                                                                                              (+ current-spot col)) ; The knight goes down
      :else (recur (inc start-health) (inc start-health) 0) ; if the knight would die either direction, reset at the top of the dungeon with 1 additional health
      )))
