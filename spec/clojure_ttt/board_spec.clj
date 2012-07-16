(ns clojure-ttt.board-spec
  (:use speclj.core)
  (:use clojure-ttt.board))

(describe "new-board"
          (it " hold blank board"
              (should= [nil nil nil nil nil nil nil nil nil] (new-board))))

(describe "winner?"
          (it "returns nil if there is no winner"
              (should= nil (winner? "X" #{0 1 3 7 8})))
          (it "returns true if there is a winner"
              (should (winner? "O" '("O" "O" "O" nil nil nil nil nil nil))))
          (it "returns false if the input player is not the winner"
              (should-not (winner? "X" '("O" "O" "O" nil nil nil nil nil nil)))))

(describe "empty-cells"
          (it "returns a set of nil (empty cells)"
              (should= 9 (count (empty-cells (new-board))))
              (should= #{3 4 5 6 7 8} (empty-cells '("X" "O" "X" nil nil nil nil nil nil)))))

(describe "update-board"
          (it "updates the board with new cell"
              (should= [nil "X" nil nil nil nil nil nil nil] (update-board (new-board) 1 "X"))
              (should= ["O"] (update-board [nil] 0 "O"))))

(run-specs)
