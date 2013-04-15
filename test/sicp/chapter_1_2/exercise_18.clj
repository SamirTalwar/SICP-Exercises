(ns sicp.chapter-1-2.exercise-18
  (:use clojure.test))

(defn multiply [b n]
  (defn multiply-iter [a b n]
    (defn double-value [x] (+ x x))
    (defn halve [x] (/ x 2))
    (cond (= n 0) 0
          (= n 1) (+ a b)
          (even? n) (multiply-iter a (double-value b) (halve n))
          :else (multiply-iter (+ b a) b (dec n))))
  (multiply-iter 0 b n))

(deftest exercise-18
         (is (= 1 (multiply 1 1)))
         (is (= 3 (multiply 1 3)))
         (is (= 6 (multiply 6 1)))
         (is (= 6 (multiply 3 2)))
         (is (= 12 (multiply 6 2)))
         (is (= 20 (multiply 5 4)))
         (is (= 27 (multiply 9 3)))
         (is (= 20 (multiply 2 10)))
         (is (= 25 (multiply 5 5)))
         (is (= 72 (multiply 12 6))))
