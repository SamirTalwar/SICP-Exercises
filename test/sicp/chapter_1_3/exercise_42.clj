(ns sicp.chapter-1-3.exercise-42
  (:use clojure.test))

(defn compose [f g]
  #(f (g %)))

(defn square [x]
  (* x x))

(deftest exercise-42
         (is (= 4 ((compose inc inc) 2)))
         (is (= 7 ((compose #(/ % 2) #(+ % 5)) 9)))
         (is (= 49 ((compose square inc) 6))))
