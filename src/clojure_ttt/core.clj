(ns clojure-ttt.core
  (:use clojure-ttt.game)
  (:use clojure-ttt.board)
  (:use clojure-ttt.display)
  (:use clojure-ttt.negamax))

(defn -main []
  (play-game)
)
