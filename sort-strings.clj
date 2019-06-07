(defn solve
  [string]
  (apply str (sort-by clojure.string/upper-case (apply str (clojure.string/split string #" ")))))


(defn input
  [len]
  (apply str (repeatedly len #(rand-nth (map char (flatten (list '(32) (range 65 91) (range 97 123))))))))

(defn sort-string-test-cases
  "Takes a sequence of inputs and gives IO test cases of the form
   [input output]."
  [inputs]
  (map (fn [in]
          (vector in
              (apply str (sort-by clojure.string/upper-case (apply str (clojure.string/split in #" "))))))
       inputs))


(input (inc(lrand-int 100)))
