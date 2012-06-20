(ns clojure-ttt.game
  (:use clojure-ttt.board)
  (:use clojure-ttt.display))

(defn get-input [limit]
  (print "-> ")
  (flush)
  (let [input (read-string (read-line))]
    (if (.contains limit input)
      input
      (do
        (println "\7")
        (recur limit)))))


(defn switch-player [current-player]
  (if (= "X" current-player) "O" "X"))

(defn stalemate? [board]
  (if (= 0 (count (empty-cells board))) true false))

(defn game-over? [board]
  (some true?
        [(or (winner? "X" board) (winner? "O" board))
         (stalemate? board)]))

(defn start [player]
  (let [new-board (new-board)
        player-move (fn [player board] (if (= player :computer) (print board) (get-input (empty-cells board))))]
  (loop [board new-board
         current-player "X"]
    (print-game (format "M: %s   P: %s", (count (empty-cells board)) current-player) "Your Move" board)
    (if (game-over? board)
      (do
        (if (stalemate? board)
          (print-game "GAME OVER" "STALEMATE!" board)
          (print-game "GAME OVER" (format "%s WINS!", (switch-player current-player)) board)))
      (do
        (recur
          (update-board board (player-move player board) current-player)
          (switch-player current-player)))))))

(defn play-game []
  (print-menu)
  (case (get-input (range 3))
    1 (start :computer)
    2 (start :human)
    0 (do (println "Goodbye!")(System/exit 0))))

