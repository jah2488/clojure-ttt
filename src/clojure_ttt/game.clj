(ns clojure-ttt.game
  (:use clojure-ttt.board)
  (:use clojure-ttt.display)
  (:use clojure-ttt.negamax)
  (:use clojure-ttt.utils))

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

(print-end-game [current-player board]
  (if (stalemate? board)
    (print-stalemate board)
    (print-winner current-player board)))

(defn start [player]
  (let [new-board (new-board)]
    (loop [board new-board
           current-player "X"]
        (print-game (count (empty-cells board)) current-player board)
        (if (game-over? board)
          (do (print-end-game current-player board) (end))
            (recur
              (update-board board (player-move player board current-player) current-player)
              (switch-player current-player))))))

(defn play-game []
  (print-menu)
  (case (get-input (range 3))
    1 (start :computer)
    2 (start :human)
    0 (end)))

