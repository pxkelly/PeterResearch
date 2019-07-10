(defn solve
  [in]
  (Integer/toBinaryString in))


(defn checker
  [result]
  (reduce + (map #(if (clojure.string/includes? "01" (str (first %))) 0 (second %)) (frequencies (str result)))))
