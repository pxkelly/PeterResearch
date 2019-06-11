(defn solve
  [in]
  (loop [index 0 newStr ""]
    (if (= index (count in)) (apply str (drop-last newStr))
        (recur (inc index) (str newStr (clojure.string/capitalize (apply str (repeat (inc index) (nth in index)))) "-")))))

(defn input
  [len]
  (let [chars-between #(map char (range (int %1) (inc (int %2))))
        chars (concat (chars-between \a \z)
                      (chars-between \A \Z))
        word (take len (repeatedly #(rand-nth chars)))]
          (reduce str word)))
