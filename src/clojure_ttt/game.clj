(ns clojure-ttt.game)


(defn start []
  (println "SinglePlayerGameStarted")
)

(defn play-game []
  (println "")
  (println "+---------------+")
  (println "|  Tic Tac Toe  |")
  (println "+---------------+")
  (println "|               |")
  (println "|      (1)      |")
  (println "|      (2)      |")
  (println "|      (Q)      |")
  (println "|               |")
  (println "+---------------+")
  (println "")
  (print "-> ")

  (flush)
  (case (read-line)
    "1" (start )
    "2" (println "Multiplayer  game!")
    "Q" (println "QUIT")))
