(ns sicp.chapter-1.exercise-1
  (:use clojure.test))

(deftest exercise-1
         (is (= 10 10))
         (is (= 12 (+ 5 3 4)))
         (is (= 8 (- 9 1)))
         (is (= 3 (/ 6 2)))
         (is (= 6 (+ (* 2 4) (- 4 6))))
         (def a 3)
         (is (= 3 a))
         (def b (+ a 1))
         (is (= 4 b))
         (is (= 19 (+ a b (* a b))))
         (is (= false (= a b)))
         (is (= 4
                (if (and (> b a) (< b (* a b)))
                  b
                  a)))
         (is (= 16
                (cond (= a 4) 6
                      (= b 4) (+ 6 7 a)
                      true 25)))
         (is (= 6 (+ 2 (if (> b a) b a))))
         (is (= 16
                (* (cond (> a b) a
                         (< a b) b
                         true -1)
                   (+ a 1)))))
