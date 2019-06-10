(defn solve
  [input]
  (apply str (map char (map #(if (<= (+ 13 (int %)) 122)
                                       (+ 13 (int %))
                                       (+ 96 (mod (+ 13 (int %)) 122))) input))))

(defn input
  [len]
  (apply str (map char (repeatedly len #(+ (rand-int 26) 97)))))
