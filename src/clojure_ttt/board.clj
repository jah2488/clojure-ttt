(ns clojure-ttt.board
  (:use clojure.set))

(def winning-moves [#{0 1 2} #{3 4 5} #{6 7 8} #{0 3 6} #{1 4 7} #{2 5 8} #{0 4 8} #{6 4 2}] )

(defn player-moves [player board]
  (into #{} (keep-indexed #(if (= player %2) %1) board)))

(defn winner? [player board]
  (some (fn [cell] (subset? cell (player-moves player board))) winning-moves))

(defn empty-cells [board]
  (count (player-moves nil board)))

(defn new-board []
  '(nil nil nil
    nil nil nil
    nil nil nil))

(defn print-cell [board index]
  (if (= nil (nth board index))
    (print (str (str " " index) " "))
    (print (str (str " " (nth board index)) " "))))

(defn print-board [board]
  (do
    (print-cell board 0)
    (print "|")
    (print-cell board 1)
    (print "|")
    (print-cell board 2)
    (println "")
    (println "---+---+---")
    (print-cell board 3)
    (print "|")
    (print-cell board 4)
    (print "|")
    (print-cell board 5)
    (println "")
    (println "---+---+---")
    (print-cell board 6)
    (print "|")
    (print-cell board 7)
    (print "|")
    (print-cell board 8)
    (println "")
    ))

(defn update-board [board cell player]
  (concat
    (take cell board)
    (list player)
    (nthnext board (inc cell))))
