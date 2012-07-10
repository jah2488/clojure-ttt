(ns clojure-ttt.utils
  (:use [clojure-ttt.board :only [empty-cells winner?]]))

(defn switch-player [current-player]
  (if (= "X" current-player) "O" "X"))

(defn stalemate? [board]
  (if (= 0 (count (empty-cells board))) true false))

(defn game-over? [board]
  (some true?
        [(or (winner? "X" board) (winner? "O" board))
         (stalemate? board)]))

(defn end [] (println "Goodbye!") (shutdown-agents))
