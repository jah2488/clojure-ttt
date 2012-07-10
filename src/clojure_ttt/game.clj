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

(defn start [player]
  (let [new-board (new-board)]
    (loop [board new-board
          current-player "X"]
      (let [player-move (fn [player board] (if (and (= player :computer) (= current-player "O"))
                                             (best-move board "O")
                                             (get-input (empty-cells board))))]
        (print-game
          (format "M: %s   P: %s", (count (empty-cells board)) current-player) "Your Move" board)
        (if (game-over? board)
          (do
            (if (stalemate? board)
              (print-stalemate board)
              (print-winner current-player board))
            (end))
            (recur
                (update-board board (player-move player board) current-player)
                (switch-player current-player)))))))

(defn play-game []
  (print-menu)
  (case (get-input (range 3))
    1 (start :computer)
    2 (start :human)
    0 (end)))

