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
  (Math/abs (- (eval (list program)) testCase)))

; Returns the program that is better (less overall fitness score)
(defn tournament
  [programList testCase]
  (if (> (testFitness (nth programList 0) testCase)
         (testFitness (nth programList 1) testCase))
    (nth sel 1)
    (nth sel 0)))

; Choose the parents from the program list to be put up in the tournament
(defn chooseSelection
  [programList]
  (def sel (list (nth programList (rand-int (count programList)))))
  (def nextChoice (remove #{sel} programList))
  (conj sel (nth nextChoice (rand-int (count nextChoice)))))

; This will run the whole program
(defn main
  [testCase]
  (def programList (list (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])
                         (generateProgram [3 1 8 2 7] ['+])))
  (tournament (chooseSelection programList) testCase))

; Error I'm having:
; It doesn't like "(eval program)" I think
