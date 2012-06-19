(ns clojure-ttt.core
  (:use clojure-ttt.game)
  (:use clojure-ttt.board)
  (:use clojure.set))

(defn -main []
  (play-game)
)

(defn hello [& {:keys [x] :or {x "hello"}}] (println x))
