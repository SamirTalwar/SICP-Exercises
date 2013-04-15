(ns sicp.chapter-1-1.exercise-07
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn square [x] (* x x))

(defn good-enough? [guess previous-guess]
  (< (math/abs (- guess previous-guess)) 0.001))

(defn sqrt-iter [guess previous-guess x]
  (if (good-enough? guess previous-guess)
    guess
    (sqrt-iter (improve guess x) guess x)))

(defn sqrt [x]
  (sqrt-iter 1.0 0.0 x))


(defn almost-equal [a b]
  (< (math/abs (- a b)) 0.001))

(deftest exercise-07
         (is (almost-equal 3.0 (sqrt 9)))
         (is (almost-equal 11.7047 (sqrt (+ 100 37))))
         (is (almost-equal 1.7738 (sqrt (+ (sqrt 2) (sqrt 3)))))
         (is (almost-equal 1000.0 (square (sqrt 1000))))
         ; The following tests do not work with the original `sqrt` implementation.
         (is (almost-equal 0.001 (sqrt 0.000001)))
         (is (almost-equal 3037000499.97605 (sqrt Long/MAX_VALUE))))
