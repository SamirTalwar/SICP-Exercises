(ns sicp.chapter-1-3.exercise-30
  (:use clojure.test)
  (:require [sicp.comparisons :refer [approximately-within?]]))

(defn sum [term a next-value b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (iter (next-value a) (+ result (term a)))))]
    (iter a 0)))

(defn sum-cubes [a b]
  (sum (fn [n] (* n n n)) a inc b))

(defn sum-integers [a b]
  (sum identity a inc b))

(defn pi-sum [a b]
  (letfn [(pi-term [x] (/ 1.0 (* x (+ x 2))))
          (pi-next [x] (+ x 4))]
    (sum pi-term a pi-next b)))

(def approximately? (approximately-within? 0.01))

(deftest exercise-30
         (is (= 3025 (sum-cubes 1 10)))
         (is (= 55 (sum-integers 1 10)))
         (is (approximately? Math/PI (* 8 (pi-sum 1 1000)))))
