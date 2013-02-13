(ns sicp.chapter-1-3.exercise-35
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(def tolerance 0.00001)
(defn approximately? [v1 v2]
  (< (math/abs (- v1 v2)) tolerance))

(defn fixed-point [f first-guess]
  (defn attempt [guess]
    (let [next-guess (f guess)]
      (if (approximately? guess next-guess)
        next-guess
        (recur next-guess))))
  (attempt first-guess))

(def golden-ratio (/ (+ 1 (math/sqrt 5)) 2))

(deftest exercise-35
         (is (approximately? golden-ratio (fixed-point (fn [x] (+ 1 (/ 1 x))) 1))))
