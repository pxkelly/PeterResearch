(defn solve
  [in]
  (let [right-rot (Integer/parseInt (apply str (concat (str (last (str in))) (butlast (str in)))))]
    (mod right-rot in)))
