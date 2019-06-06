(require '[clojure.string :as str])

(defn solve
  [input]
  (if (or (= (str input) "") (every? #{\-} (str input))) ""
      (let [full-string (str/join (map str/capitalize (str/split (str input) #"-")))]
           (apply str (str/lower-case (first full-string)) (drop 1 full-string)))))

(defn make-input
  [len]
  (let [chars-between #(map char (range (int %1) (inc (int %2))))
        chars (concat (chars-between \a \z)
                      " "
                      "-")
        word (take len (repeatedly #(rand-nth chars)))]
          (reduce str word)))


(defn camel-case-test-cases
  "Takes a sequence of inputs and gives IO test cases of the form
   [input output]."
  [inputs]
  (map (fn [in]
          (vector in
            (if (= in "") ""
                (let [full-string (clojure.string/join (map clojure.string/capitalize (clojure.string/split in #"-")))]
                     (apply str (clojure.string/lower-case (first full-string)) (drop 1 full-string))))))
       inputs))
