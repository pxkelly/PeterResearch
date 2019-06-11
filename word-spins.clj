(defn solve
  [in]
  (clojure.string/join " " (map #(if (>= (count %) 5) (apply str (reverse %)) %) (clojure.string/split in #" "))))

(defn input
  [len]
  (let [chars-between #(map char (range (int %1) (inc (int %2))))
        chars (concat (chars-between \a \z)
                      " ")
        word (take len (repeatedly #(rand-nth chars)))]
          (reduce str word)))
