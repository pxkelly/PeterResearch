(ns loan_calc.clj)
(require 'clojure.math)

(defn solve
  [amount interest time]
  (let [percent-over-months (/ interest time)
        divisor (/ (dec (expt (inc percent-over-months) time))
                   (* percent-over-months (expt (inc percent-over-months) time)))]
      (/ amount divisor)))
; interest / time = percent over months
; (((1 + percent-over-months) ^ time) - 1) / (percent-over-months * (1 + percent-over-months) ^ time) = D
; amount / D = monthly payment
