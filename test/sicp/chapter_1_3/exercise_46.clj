(ns sicp.chapter-1-3.exercise-46
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn approximately? [v1 v2]
  (def tolerance 0.00001)
  (< (math/abs (- v1 v2)) tolerance))


(defn iterative-improve [improve good-enough?]
  (fn [first-guess]
    (defn attempt [guess]
      (let [next-guess (improve guess)]
        (if (good-enough? guess next-guess)
          next-guess
          (recur next-guess))))
    (attempt first-guess)))

(defn sqrt [x]
  (defn average [x y]
    (/ (+ x y) 2))
  (defn improve [guess]
    (average guess (/ x guess)))
  ((iterative-improve improve approximately?) 1.0))

(defn fixed-point [f first-guess]
  ((iterative-improve f approximately?) first-guess))


(defn square [x] (* x x))

(def golden-ratio (/ (+ 1 (math/sqrt 5)) 2))

(deftest exercise-46
         (is (approximately? 3.0 (sqrt 9)))
         (is (approximately? 11.7047 (sqrt (+ 100 37))))
         (is (approximately? 1.77377 (sqrt (+ (sqrt 2) (sqrt 3)))))
         (is (approximately? 1000.0 (square (sqrt 1000))))
         ; The following tests do not work with the original `sqrt` implementation.
         (is (approximately? 0.001 (sqrt 0.000001)))
         (is (approximately? 3037000499.97605 (sqrt Long/MAX_VALUE)))

         (is (approximately? golden-ratio (fixed-point (fn [x] (+ 1 (/ 1 x))) 1))))
