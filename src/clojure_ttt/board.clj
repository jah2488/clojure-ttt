(ns clojure-ttt.board
  (:use clojure.set))

(def winning-moves [#{0 1 2} #{3 4 5} #{6 7 8} #{0 3 6} #{1 4 7} #{2 5 8} #{0 4 8} #{6 4 2}] )

(defn player-moves [player board]
  (into #{} (keep-indexed #(if (= player %2) %1) board)))

(defn winner? [player board]
  (some (fn [cell] (subset? cell (player-moves player board))) winning-moves))

(defn empty-cells [board]
  (player-moves nil board))

(defn new-board []
  [nil nil nil
    nil nil nil
    nil nil nil])

(defn update-board [board cell player]
  (concat
    (take cell board)
    (list player)
    (nthnext board (inc cell))))
