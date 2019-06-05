(require '[clojure.string :as str])

(defn solve
  [input]
  (if (= input "") ""
      (let [full-string (str/join (map str/capitalize (str/split input #"-")))]
           (apply str (str/lower-case (first full-string)) (drop 1 full-string)))))

(defn make-input
  [len]
  (let [chars-between #(map char (range (int %1) (inc (int %2))))
        chars (concat (chars-between \a \z)
                      " "
                      "-")
        word (take len (repeatedly #(rand-nth chars)))]
          (reduce str word)))
