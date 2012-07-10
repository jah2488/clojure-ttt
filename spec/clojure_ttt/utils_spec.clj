(ns clojure-ttt.utils-spec
  (:use speclj.core)
  (:use clojure-ttt.utils)
  (:use clojure-ttt.board))

(describe "switch-player"
          (it "should recieve x and return o"
              (should= "O" (switch-player "X")))
          (it "should recieve o and return x"
              (should= "X" (switch-player "O"))))

(describe "stalemate?"
          (it "should return true if there are no moves left"
              (should     (stalemate? '("X"))))
          (it "should return false if there are moves left"
              (should-not (stalemate? (new-board)))))

(describe "game-over?"
          (it "should return true if player has won"
              (should     (game-over? '("X" "X" "X" nil nil nil nil nil nil)))
          (it "should return false is nobody has won and there are moves left"
              (should-not (game-over? (new-board))))))

(describe "end"
          (context "Needs to stub SystemExit to be properly tested"
          (it "prints goodbye")))


(run-specs)
