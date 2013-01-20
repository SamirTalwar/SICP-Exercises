(ns sicp.chapter-1.exercise-3
  (:use clojure.test))

(defn square [x] (* x x))

(defn sum-of-squares-of-larger-two [a b c]
  (cond (and (>= a b) (>= b c)) (+ (square a) (square b))
        (and (>= a b) (>= c b)) (+ (square a) (square c))
        true (+ (square b) (square c))))

(deftest exercise-3
         (is (= (sum-of-squares-of-larger-two 3 4 5) 41))
         (is (= (sum-of-squares-of-larger-two 7 6 2) 85))
         (is (= (sum-of-squares-of-larger-two 9 4 5) 106)))
