(ns clojure-ttt.game-spec
  (:use speclj.core)
  (:use clojure-ttt.game)
  (:use clojure-ttt.board))

(describe "switch-player"
          (it "should recieve x and return o"
              (should= "O" (switch-player "X")))
          (it "should recieve o and return x"
              (should= "X" (switch-player "O"))))

(describe "game-over?"
          (it "should return true if player has won"
              (should     (game-over? '("X" "X" "X" nil nil nil nil nil nil)))
          (it "should return false is nobody has won and there are moves left"
              (should-not (game-over? (new-board))))))
(run-specs)
