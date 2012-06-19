(ns clojure-ttt.game
  (:use clojure-ttt.board))

(defn get-input []
  (print "-> ")
  (flush)
  (let [input (read-string (read-line))]
    (println input)
    (if (.contains (range 9) input)
      input
      (recur))))


(defn switch-player [current-player]
  (if (= "X" current-player) "O" "X"))

(defn stalemate? [board]
  (if (= 0 (empty-cells board)) true false))

(defn game-over? [board]
  (some true?
        [[#(winner? % board) '("X" "O")]
         [(stalemate? board)]]))


(defn start []
  (let [new-board (new-board)]
  (loop [board new-board
         current-player "X"]
    (println (format "%s Moves Remaining", (empty-cells board)))
    (print-board board)
    (if (game-over? board)
      (do
        (println "GAME OVER")
        (if (stalemate? board)
          (println "Stalemate!")
          (println (format "Player %s Wins", (switch-player current-player)))))
      (do
        (println (format "Its Your Turn %s", current-player))
        (recur
          (update-board board (get-input) current-player)
          (switch-player current-player)))))))

(defn play-game []
  (println "")
  (println "+---------------+")
  (println "|  Tic Tac Toe  |")
  (println "+---------------+")
  (println "|               |")
  (println "|      (1)      |")
  (println "|      (2)      |")
  (println "|      (0)      |")
  (println "|               |")
  (println "+---------------+")
  (println "")

  (try (case (get-input)
    1 (start )
    2 (println "Multiplayer  game!")
    Q (println "QUIT"))
  (catch IllegalArgumentException e nil))
  (recur))
