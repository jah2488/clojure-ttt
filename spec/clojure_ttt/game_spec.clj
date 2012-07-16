(ns clojure-ttt.game-spec
  (:use speclj.core)
  (:use clojure-ttt.game)
  (:use [clojure-ttt.board :only [new-board]]))

(describe "prompt"
          (it "prints the prompt text"
              (should= "-> " (with-out-str (prompt)))))

(describe "get-input"
          (it "ensures valid input from the user"
              (should= "-> " (with-out-str (with-in-str "8" (get-input (range 9)))))
              (should= 8     (with-in-str "8" (get-input (range 9))))))

(describe "player-move"
          (it "gets input from the player if its the player's turn"
              (should= 5 (with-in-str "5" (player-move "X" (new-board) :human))))
          (it "gets the computers move if its the computer's turn"
              (should= 4 (player-move :computer ["X" "X" "X"
                                                 "X" nil "X"
                                                 "X" "X" "X"] "O"))))

(describe "print-end-game"
          (it "prints stalemate board if game ends in stalemate"
              (should= "GAME OVER" (re-find #"GAME OVER" (with-out-str (print-end-game "X" (new-board))))))
          (it "prints end game with winner if no stalemate"
              (should= "STALEMATE" (re-find #"STALEMATE" (with-out-str (print-end-game "X" ["X" "X" "O"
                                                                                            "O" "X" "X"
                                                                                            "X" "O" "O"]))))))
(describe "start"
          (it "should ask for user input and end the game"
              (should= "GAME OVER" (re-find #"GAME OVER" (with-out-str (with-in-str "4" (start :human ["X" "X" "O"
                                                                                                       "O" nil "O"
                                                                                                       "X" "O" "X"])))))))

(run-specs)

