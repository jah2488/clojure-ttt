(ns clojure-ttt.core
  (:use clojure-ttt.game)
  (:use clojure-ttt.board)
  (:use clojure-ttt.display))

(defn -main []
  (play-game)
)
