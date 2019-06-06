(ns clojush.problems.software.roman-numerals
    (:require [clojure.string :as string]))

(def roman-numeral-values
                         {"I" 1
                          "IV" 4
                          "V" 5
                          "IX" 9
                          "X" 10
                          "XL" 40
                          "L" 50
                          "XC" 90
                          "C" 100
                          "CD" 400
                          "D" 500
                          "CM" 900
                          "M" 1000})

(defn roman-numerals-input
 "Makes a Roman Numeral input given a decimal number."
 [num]
 (apply str
           (loop [roman "" number num]
             (cond
               (> number (mod number 1000)) (recur (str roman "M") (- number 1000))
               (> number (mod number 900)) (recur (str roman "CM") (- number 900))
               (> number (mod number 500)) (recur (str roman "D") (- number 500))
               (> number (mod number 400)) (recur (str roman "CD") (- number 400))
               (> number (mod number 100)) (recur (str roman "C") (- number 100))
               (> number (mod number 90)) (recur (str roman "XC") (- number 90))
               (> number (mod number 50)) (recur (str roman "L") (- number 50))
               (> number (mod number 40)) (recur (str roman "XL") (- number 40))
               (> number (mod number 10)) (recur (str roman "X") (- number 10))
               (> number (mod number 9)) (recur (str roman "IX") (- number 9))
               (> number (mod number 5)) (recur (str roman "V") (- number 5))
               (> number (mod number 4)) (recur (str roman "IV") (- number 4))
               (> number (mod number 1)) (recur (str roman "I") (- number 1))
               :else roman))))

(defn solve
  [in]
  (vector in
      (loop [roman in number 0]
        (cond
          (= (take 2 roman) '(\I \V)) (recur (drop 2 roman) (+ number 4))
          (= (take 2 roman) '(\I \X)) (recur (drop 2 roman) (+ number 9))
          (= (take 2 roman) '(\X \L)) (recur (drop 2 roman) (+ number 40))
          (= (take 2 roman) '(\X \C)) (recur (drop 2 roman) (+ number 90))
          (= (take 2 roman) '(\C \D)) (recur (drop 2 roman) (+ number 400))
          (= (take 2 roman) '(\C \M)) (recur (drop 2 roman) (+ number 900))
          (= (first roman) \M) (recur (drop 1 roman) (+ number 1000))
          (= (first roman) \D) (recur (drop 1 roman) (+ number 500))
          (= (first roman) \C) (recur (drop 1 roman) (+ number 100))
          (= (first roman) \L) (recur (drop 1 roman) (+ number 50))
          (= (first roman) \X) (recur (drop 1 roman) (+ number 10))
          (= (first roman) \V) (recur (drop 1 roman) (+ number 5))
          (= (first roman) \I) (recur (drop 1 roman) (+ number 1))
          :else number))))

(defn tester
  []
  (roman-numerals-input (inc (rand-int 4000))))
