(ns clojure-ttt.display-spec
  (:use speclj.core)
  (:use clojure-ttt.display))


(describe "cstr"
          (it "should return the given with the color provided"
              (should= (str "\033[32mHi!\033[m") (cstr green "Hi!"))))

(describe "white-space"
          (it "should return the amount of padding based on length"
              (should= 6 (white-space "---" 15))))

(describe "center"
          (it "should print the message centered"
              (should= (str "  " (cstr green "Tic Tac Toe") "  ") (center green "Tic Tac Toe"))
              (should= (str "      " (cstr magenta "(1)") "      ") (center magenta "(1)"))))

(describe "get-cell"
          (it "should print the given cell"
              (should= (cstr green " X ") (get-cell '("X") 0)))
          (it "should print the index number if cell is nil"
              (should= (str " " (cstr pink "0") " ")  (get-cell '(nil) 0))))

(run-specs)
