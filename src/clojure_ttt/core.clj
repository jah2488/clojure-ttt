(ns clojure-ttt.core
  (:use clojure-ttt.game))

(defn -main []
  (play-game)
)

(defn hello [& {:keys [x] :or {x "hello"}}] (println x))
