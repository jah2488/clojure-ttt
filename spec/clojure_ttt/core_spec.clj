(ns clojure-ttt.core-spec
    (:use speclj.core)
    (:use clojure-ttt.core))


(describe "hello"
          (it "should print hello"
              (should= (str "hello\n") (with-out-str (hello)))
              (should= (str "variable-injection\n") (with-out-str (hello :x "variable-injection")))))

(run-specs)
