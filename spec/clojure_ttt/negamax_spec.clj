(ns clojure-ttt.negamax-spec
  (:use speclj.core)
  (:use clojure-ttt.negamax)
  (:use clojure-ttt.board)
  (:use clojure-ttt.utils))

(describe 'best-move'
          (it "should return the best move for the computer"
              (should= 6 (best-move '("X" "O" "X"
                                      "X" nil "O"
                                      nil nil "O") "X"))
              (should= 6 (best-move '("O" "X" "O"
                                      "O" nil "X"
                                      nil nil "X") "X"))
              (should= 2 (best-move '(nil nil nil
                                      "O" "O" "X"
                                      nil "O" "X") "X"))
              (should= 2 (best-move '("X" "X" nil
                                      nil "O" "O"
                                      nil nil "O") "X"))
              (should= 4 (best-move '("X" "O" nil
                                      nil nil "O"
                                      nil "O" "X") "X"))
              (should= 6 (best-move '("X" "O" "X"
                                      "O" "X" "O"
                                      nil "X" "O") "X"))))

(run-specs)
