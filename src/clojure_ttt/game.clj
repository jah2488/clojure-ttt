(ns clojure-ttt.game
  (:use [clojure-ttt.board   :only [empty-cells new-board update-board]])
  (:use [clojure-ttt.display :only [print-stalemate print-winner print-game print-menu]])
  (:use [clojure-ttt.negamax :only [best-move]])
  (:use [clojure-ttt.utils   :only [switch-player game-over? stalemate? end]]))

(defn prompt [] (print "-> "))

(defn get-input [limit]
  (prompt)
  (flush)
  (let [input (try (read-string (read-line)) (catch Exception e))]
    (if (.contains limit input)
      input
      (do
        (println "\7")
        (recur limit)))))

(defn player-move [player board current-player]
  (if (and (= player :computer) (= current-player "O"))
    (best-move board "O")
    (get-input (empty-cells board))))

(defn print-end-game [current-player board]
  (if (stalemate? board)
    (print-stalemate board)
    (print-winner current-player board)))

(defn start [player new-board]
    (loop [board new-board
           current-player "X"]
        (print-game (count (empty-cells board)) current-player board)
        (if (game-over? board)
          (do (print-end-game current-player board) (end))
          (recur
            (update-board board (player-move player board current-player) current-player)
            (switch-player current-player)))))


(defn play-game []
  (print-menu)
  (case (get-input (range 3))
    1 (start :computer (new-board))
    2 (start :human    (new-board))
    0 (end)))

