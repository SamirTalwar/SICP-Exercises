(ns sicp.chapter-1-1.exercise-08
  (:use clojure.test)
  (:require [sicp.comparisons :refer [approximately-within?]]
            [clojure.contrib.math :as math]))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ (+ (/ x (* guess guess)) (* 2 guess)) 3)))

(defn good-enough? [guess previous-guess]
  (< (math/abs (- guess previous-guess)) 0.000001))

(defn cbrt-iter [guess previous-guess x]
  (if (good-enough? guess previous-guess)
    guess
    (cbrt-iter (improve guess x) guess x)))

(defn cbrt [x]
  (cbrt-iter 1.0 0.0 x))


(defn cube [x] (* x x x))

(def approximately? (approximately-within? 0.001))

(deftest exercise-08
         (is (approximately? 3.0 (cbrt 27.0)))
         (is (approximately? 11.447142 (cbrt 1500.0)))
         (is (approximately? 1.392850 (cbrt (+ (cbrt 2) (cbrt 3)))))
         (is (approximately? 1000.0 (cube (cbrt 1000.0))))
         (is (approximately? 0.001 (cbrt 0.000000001)))
         (is (approximately? 2097152.0 (cbrt Long/MAX_VALUE))))
