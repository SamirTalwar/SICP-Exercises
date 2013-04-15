(ns sicp.chapter-1-3.exercise-30
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn sum [term a next-value b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next-value a) (+ result (term a)))))
  (iter a 0))

(defn sum-cubes [a b]
  (sum (fn [n] (* n n n)) a inc b))

(defn sum-integers [a b]
  (sum identity a inc b))

(defn pi-sum [a b]
  (defn pi-term [x]
    (/ 1.0 (* x (+ x 2))))
  (defn pi-next [x]
    (+ x 4))
  (sum pi-term a pi-next b))

(defn approximately? [a b]
  (< (math/abs (- a b)) 0.01))

(deftest exercise-30
         (is (= 3025 (sum-cubes 1 10)))
         (is (= 55 (sum-integers 1 10)))
         (is (approximately? Math/PI (* 8 (pi-sum 1 1000)))))
