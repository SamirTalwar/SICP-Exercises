(ns sicp.chapter-1-3.exercise-41
  (:use clojure.test))

(defn double-fn [f]
  (comp f f))

(defn square [x]
  (* x x))

(deftest exercise-41
         (is (= 12 ((double-fn inc) 10)))
         (is (= 256 ((double-fn square) 4)))
         (is (= 21 (((double-fn (double-fn double-fn)) inc) 5))))
