(ns clojure-ttt.game-spec
  (:use speclj.core)
  (:use clojure-ttt.game)
  (:use [clojure.test])
  (:use [clojure.contrib.mock]))

(describe "prompt"
          (it "prints the prompt text"
              (should= "-> " (with-out-str (prompt)))))

(describe "get-input"
          (it "ensures valid input from the user"
              (should= "-> " (with-out-str (with-in-str "8" (get-input (range 9)))))
              (should= 8
                       (with-in-str "8" (get-input (range 9))))))

(describe "start"
          (it "starts the main game loop"))


(run-specs)

