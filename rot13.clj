(defn solve
  [input]
  (apply str (map char (map #(if (<= (+ 13 (int %)) 122)
                                       (+ 13 (int %))
                                       (+ 96 (mod (+ 13 (int %)) 122))) input))))

(defn input
  [len]
  (apply str (map char (repeatedly len #(+ (rand-int 26) 97)))))

(defn abs [n] (max n (- n)))

; Edited code found here: http://www.learningclojure.com/2010/11/levenshtein-distance-edit-distance.html
(defn letter-error
 [in out]
   (cond
     (and (empty? in) (empty? out)) 0   ; same length strings
     (or (empty? in) (empty? out)) (* (abs (- (count in) (count out))) 25)    ; different length strings
     :else (+ (if (= (first in) (first out)) 0 (abs (- (int (first in)) (int (first out)))))
              (#'letter-error (rest in) (rest out)))))
