; Generates random programs (a single program)
(defn generateProgram
  [terminalSet functionalSet]
  (list (nth functionalSet 0)
        (nth terminalSet (rand-int (count terminalSet)))
        (nth terminalSet (rand-int (count terminalSet)))))

; Returns a positive number based on how far away the program
; was from the test case
(defn testFitness
  [program testCase]
  (Math/abs (- (eval program) testCase)))

; Returns the program that is better (less overall fitness score)
(defn tournament
  [programList testCase]
  (if (> (testFitness (nth programList 0) testCase)
         (testFitness (nth programList 1) testCase))
    (nth programList 1)
    (nth programList 0)))

; Choose the parents from the program list to be put up in the tournament
(defn chooseSelection
  [programList]
  (def sel (list (nth programList (rand-int (count programList)))))
  (def nextChoice (remove #{sel} programList))
  (conj sel (nth nextChoice (rand-int (count nextChoice)))))

; This will take two parents and cross them to make a child
(defn reproduce
  [parents]
  (def child (list (nth (nth parents 0) 1)))
  (def children (conj child (nth (nth parents 1) 2)))
  (conj children '+))

; This will create a list of x children (x is the number after counter)
(defn createChildren
  [programList testCase]
  (loop [counter 0 children '()]
    (if (= counter 6)
      children
      (do
        (def parents (list (tournament (chooseSelection programList) testCase)))
        (recur (inc counter)
               (conj children (reproduce (conj parents (tournament (chooseSelection programList) testCase)))))))))

(defn checkForAnswer
  [programList testCase]
  (loop [counter 0]
    (if (= (eval (nth programList counter)) testCase)
      true
      (do
        (if (= counter (- (count programList) 1))
          false
          (recur (inc counter)))))))

(defn returnAnswer
  [programList testCase]
  (loop [counter 0]
    (if (= (eval (nth programList counter)) testCase)
      (nth programList counter)
      (recur (inc counter)))))

; This will run the whole program
(defn main
  [testCase]
  (def programList (list (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])))
  (loop [counter 1 programs programList testCase testCase]
    (if (= counter 4)
      false
      (do
        (if (checkForAnswer programs testCase)
          (do
            (println counter)
            (returnAnswer programs testCase))
          (recur (inc counter)
                 (createChildren programs testCase)
                 testCase))))))
