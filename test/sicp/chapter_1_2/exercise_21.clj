(ns sicp.chapter-1-2.exercise-21
  (:use clojure.test))

(defn smallest-divisor [n]
  (letfn [(divides? [a b]
            (= (rem b a) 0))
          (find-divisor [n test-divisor]
            (cond (> (* test-divisor test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (find-divisor n (inc test-divisor))))]
    (find-divisor n 2)))

(deftest exercise-21
         (is (= 199 (smallest-divisor 199)))
         (is (= 1999 (smallest-divisor 1999)))
         (is (= 7 (smallest-divisor 19999))))
