(ns sicp.chapter-1-2.exercise-16
  (:use clojure.test))

(defn fast-expt [b n]
  (defn fast-expt-iter [a b n]
    (defn square [x] (* x x))
    (cond (= n 0) 1
          (= n 1) (* a b)
          (even? n) (fast-expt-iter a (square b) (/ n 2))
          :else (fast-expt-iter (* b a) b (dec n))))
  (fast-expt-iter 1 b n))

(deftest exercise-16
         (is (= 1 (fast-expt 1 1)))
         (is (= 1 (fast-expt 1 3)))
         (is (= 6 (fast-expt 6 1)))
         (is (= 9 (fast-expt 3 2)))
         (is (= 36 (fast-expt 6 2)))
         (is (= 625 (fast-expt 5 4)))
         (is (= 729 (fast-expt 9 3)))
         (is (= 1024 (fast-expt 2 10)))
         (is (= 3125 (fast-expt 5 5)))
         (is (= 2985984 (fast-expt 12 6))))
