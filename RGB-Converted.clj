(defn solve
  [input]
  (format "%02X%02X%02X" (first input) (second input) (last input)))

(defn input
  []
  (vector (rand-int 256) (rand-int 256) (rand-int 256)))
