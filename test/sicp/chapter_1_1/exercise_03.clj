(ns sicp.chapter-1-1.exercise-03
  (:use clojure.test))

(defn square [x] (* x x))

(defn sum-of-squares-of-larger-two [a b c]
  (cond (>= a b c) (+ (square a) (square b))
        (>= a c b) (+ (square a) (square c))
        :else (+ (square b) (square c))))

(deftest exercise-03
         (is (= 41 (sum-of-squares-of-larger-two 3 4 5)))
         (is (= 85 (sum-of-squares-of-larger-two 7 6 2)))
         (is (= 106 (sum-of-squares-of-larger-two 9 4 5))))
