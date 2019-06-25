; Input is number of rows, num cols, vector of integers of that size

(defn create-dungeon
  [col]
  (vec (repeatedly col #(- (rand-int 201) 100))))

(defn solve
  [dungeon]
  (loop [start-health 1 health-remain 1 current-row 0 current-col 0]
    (cond
      (<= (+ health-remain (nth (nth dungeon current-row) current-col)) 0) (recur (inc start-health) (inc start-health) 0 0) ; if the current spot kills you, then restart
      (= (* (inc current-row) (inc current-col)) (* (count dungeon) (count (first dungeon)))) (if (<= (+ health-remain (nth (nth dungeon current-row) current-col)) 0) ; if the knight made it to the end, check if the final spot kills him
                                                                                            (recur (inc start-health) (inc start-health) 0 0)
                                                                                            start-health) ; if it does, restart, otherwise, return the health
      (and (> (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon current-row [-1000]) (inc current-col) -1000)) 0)  ; if the knight could survive both directions
           (> (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon (+ current-row 1) [-1000]) current-col -1000)) 0))
              (if (> (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon current-row [-1000]) (inc current-col) -1000))
                     (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon (+ current-row 1) [-1000]) current-col -1000)))
                    (recur   ; If going right results in having more health, go right
                      start-health
                      (+ health-remain (nth (nth dungeon current-row) current-col))
                      current-row
                      (+ current-col 1))
                    (recur  ; otherwise, go down
                      start-health
                      (+ health-remain (nth (nth dungeon current-row) current-col))
                      (+ current-row 1)
                      current-col))
      (> (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon current-row [-1000]) (inc current-col) -1000)) 0)
          (recur
          start-health
          (+ health-remain (nth (nth dungeon current-row) current-col))
          current-row
          (+ current-col 1)) ; The knight can survive a step right, so it goes right
      (> (+ (+ health-remain (nth (nth dungeon current-row) current-col)) (nth (nth dungeon (+ current-row 1) [-1000]) current-col -1000)) 0)
          (recur
          start-health
          (+ health-remain (nth (nth dungeon current-row) current-col))
          (+ current-row 1)
          current-col) ; The knight goes down
      :else (recur (inc start-health) (inc start-health) 0 0) ; if the knight would die either direction, reset at the top of the dungeon with 1 additional health
      )))
