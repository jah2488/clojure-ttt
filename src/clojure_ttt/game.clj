(ns clojure-ttt.game
  (:use clojure-ttt.board))

(defn get-input []
  (print "-> ")
  (flush)
  (read-string (read-line)))

(defn switch-player [current-player]
  (if (= "X" current-player) "O" "X"))

(defn game-over? []
  false)
(defn start []
  (println "Single Player Game Started")
  (let [new-board (new-board)]
  (loop [board new-board
         current-player "X"]
    (if (game-over?)
      (do
        (println "GAME OVER"))
      (do
        (print-board board)
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
  (println "|      (Q)      |")
  (println "|               |")
  (println "+---------------+")
  (println "")

  (case (get-input)
    1 (start )
    2 (println "Multiplayer  game!")
    q (println "QUIT")))
