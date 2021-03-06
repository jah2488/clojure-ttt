(ns clojure-ttt.display
  (:use clojure.java.shell))

(def blank  "\t|               |")
(def border "\t+---------------+")
(def reset   "\033[m")
(def green   "32m")
(def red     "31m")
(def yellow  "33m")
(def blue    "34m")
(def magenta "35m")
(def cyan    "36m")
(def pink    "37m")

(defn random-color [player]
  (let [x-colors [green blue cyan]
        o-colors [red yellow magenta]]
    (if (= "X" player) (rand-nth x-colors) (rand-nth o-colors))))

(def random-color (memoize random-color))

(defn cstr [color message]
  (str "\033[" color message reset))

(defn white-space [message length]
  (int (/ (- length (.length message)) 2)))

(defn center [color message]
  (str (apply str (take (white-space message 15) (repeat  " "))) (cstr color message)
             (apply str (take (white-space message 15) (repeat  " ")))))

(defn println-center [color message]
  (println (str "\t|" (center color message) "|")))

(defn get-cell [board index]
  (if (= nil (nth board index))
    (str " " (cstr pink index) " ")
    (if (= "X" (nth board index))
      (cstr (random-color "X") (str " " (nth board index) " "))
      (cstr (random-color "O") (str " " (nth board index) " ")))))

(defn get-row [board index]
  (str "  "
    (get-cell board index)
    "|"
    (get-cell board (+ index 1))
    "|"
    (get-cell board (+ index 2))
    "  "))

(defn print-header [message]
  (println (:out (sh "clear")))
  (println "")
  (println border)
  (println-center green message)
  (println border))

(defn print-message [message]
  (println-center green message)
  (println border))

(defn print-board [header message board]
  (print-header header)
  (println blank)
  (println-center reset (get-row board 0))
  (println-center reset "---+---+---")
  (println-center reset (get-row board 3))
  (println-center reset "---+---+---")
  (println-center reset (get-row board 6))
  (println blank)
  (println border)
  (print-message message))

(defn print-game [cell-count current-player board]
  (print-board (format "M: %s   P: %s", cell-count current-player) "Your Move" board))

(defn print-menu []
  (print-header "Tic Tac Toe")
  (println blank)
  (println blank)
  (println-center green "(1)")
  (println-center green "(2)")
  (println-center red   "(0)")
  (println blank)
  (println blank)
  (println border)
  (print-message "Players"))

(defn print-stalemate [board]
  (print-board "GAME OVER" "STALEMATE" board))

(defn print-winner [player board]
  (print-board "GAME OVER" (format "%s WINS!", player) board))
