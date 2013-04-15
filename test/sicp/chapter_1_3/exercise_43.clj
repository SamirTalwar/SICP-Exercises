(ns sicp.chapter-1-3.exercise-43
  (:use clojure.test))

(defn compose [f g]
  #(f (g %)))

(defn repeated [f n]
  ((fn [func times]
     (if (= times 1)
       func
       (recur (compose f func) (dec times))))
     f n))

(defn square [x]
  (* x x))

(deftest exercise-43
         (is (= 20 ((repeated inc 10) 10)))
         (is (= 625 ((repeated square 2) 5))))
