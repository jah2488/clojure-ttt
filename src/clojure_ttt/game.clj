(ns clojure-ttt.game
  (:use clojure-ttt.board)
  (:use clojure-ttt.display)
  (:use clojure-ttt.negamax)
  (:use clojure-ttt.utils))

(defn get-input [limit]
  (print "-> ")
  (flush)
  (let [input (read-string (read-line))]
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
              (print-game "GAME OVER" "STALEMATE" board)
              (print-game "GAME OVER" (format "%s WINS!", (switch-player current-player)) board))
            (System/exit 0))
            (recur
                (update-board board (player-move player board) current-player)
                (switch-player current-player)))))))

(defn play-game []
  (print-menu)
  (case (get-input (range 3))
    1 (start :computer)
    2 (start :human)
    0 (do (println "Goodbye!")(System/exit 0))))

