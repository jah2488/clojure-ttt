(ns clojure-ttt.game-spec
  (:use speclj.core)
  (:use clojure-ttt.game))

(describe "switch-player"
          (it "should recieve x and return o"
              (should= "O" (switch-player "X")))
          (it "should recieve o and return x"
              (should= "X" (switch-player "O"))))

(describe "game-over?"
          (it "should return true if player has won"
              (should     (game-over?)))
          (it "should return false is nobody has won and there are moves left"
              (should-not (game-over?))))
(run-specs)
