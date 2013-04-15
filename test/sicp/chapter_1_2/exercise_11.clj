(ns sicp.chapter-1-2.exercise-11
  (:use clojure.test))

(defn f-recursive [n]
  (if (< n 3)
    n
    (+ (f-recursive (- n 1)) (* 2 (f-recursive (- n 2))) (* 3 (f-recursive (- n 3))))))

(defn f-iterative [n]
  (defn f [a b c count]
    (if (= count 0)
      a
      (f b c (+ (* 3 a) (* 2 b) c) (dec count))))
  (f 0 1 2 n))

(deftest exercise-11
         (is (= 0 (f-recursive 0)))
         (is (= 1 (f-recursive 1)))
         (is (= 2 (f-recursive 2)))
         (is (= 4 (f-recursive 3)))
         (is (= 11 (f-recursive 4)))
         (is (= 25 (f-recursive 5)))
         (is (= 59 (f-recursive 6)))
         (is (= 142 (f-recursive 7)))
         (is (= 335 (f-recursive 8)))

         (is (= 0 (f-iterative 0)))
         (is (= 1 (f-iterative 1)))
         (is (= 2 (f-iterative 2)))
         (is (= 4 (f-iterative 3)))
         (is (= 11 (f-iterative 4)))
         (is (= 25 (f-iterative 5)))
         (is (= 59 (f-iterative 6)))
         (is (= 142 (f-iterative 7)))
         (is (= 335 (f-iterative 8))))
