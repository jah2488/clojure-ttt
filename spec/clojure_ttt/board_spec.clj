(ns clojure-ttt.board-spec
  (:use speclj.core)
  (:use clojure-ttt.board))

(describe "new-board"
          (it "should hold blank board"
              (should= '(nil nil nil nil nil nil nil nil nil) (new-board))))

(describe "winner?"
          (it "should return nil if there is no winner"
              (should= nil (winner? "X" #{0 1 3 7 8})))
          (it "should return true if there is a winner"
              (should (winner? "O" '("O" "O" "O" nil nil nil nil nil nil)))
              (should-not (winner? "X" '("O" "O" "O" nil nil nil nil nil nil))))
          (it "should not work with sets anymore"
              (should (winner? "X" #{0 3 6}))
              (should (winner? "X" #{6 7 8}))
              (should (winner? "X" #{0 4 8}))
              (should (winner? "X" #{2 5 8}))
              (should (winner? "X" #{3 4 5}))))

(describe "empty-cells"
          (it "should return the count of nil (empty cells)"
              (should= 9 (empty-cells (new-board)))
              (should= 6 (empty-cells '("X" "O" "X" nil nil nil nil nil nil)))))

(describe "print-cell"
          (it "should print the given cell"
              (should= " X " (with-out-str (print-cell '("X") 0))))
          (it "should print the index number if cell is nil"
              (should= " 0 " (with-out-str (print-cell '(nil) 0)))))

(describe "print-board"
          (it "should print blank board"
              (should= " 0 | 1 | 2 \n---+---+---\n 3 | 4 | 5 \n---+---+---\n 6 | 7 | 8 \n" (with-out-str (print-board (new-board)))))
          (it "should print copy of current board"
              (should= " O | X | O \n---+---+---\n 3 | 4 | 5 \n---+---+---\n 6 | 7 | 8 \n" (with-out-str (print-board '("O" "X" "O" nil nil nil nil nil nil))))))

(describe "update-board"
          (it "should update board with new cell"
              (should= '(nil "X" nil nil nil nil nil nil nil) (update-board (new-board) 1 "X"))
              (should= '("O") (update-board '(nil) 0 "O"))))

(run-specs)
