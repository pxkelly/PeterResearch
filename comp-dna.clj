(defn solve
  [in]
  (-> in
    (clojure.string/replace "A" "T")
    (clojure.string/replace "T" "A")
    (clojure.string/replace "C" "G")
    (clojure.string/replace "G" "C")))

(defn swap
  [char]
  (cond
    (= char \A) \T
    (= char \T) \A
    (= char \C) \G
    :else \C))

(defn solve2
  [in]
  (loop [original in new ""]
    (if (= (count original) 0)
      new
      (recur (rest original) (str new (swap (first original)))))))

;; Define test cases
(defn dna-input
  "Makes a DNA input vector of length len."
  [len]
  (apply str (repeatedly len #(rand-nth (list \A \T \C \G)))))

(defn yee
  [in]
  (reduce + (map #(if (clojure.string/includes? "ATCG" (str (first %))) 0 (second %)) (frequencies (str in)))))
