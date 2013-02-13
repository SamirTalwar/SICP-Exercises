(ns sicp.chapter-1-3.exercise-37
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn cont-frac [n d k]
  (defn recurse [i]
    (if (> i k)
      0
      (/ (n i) (+ (d i) (recurse (+ i 1))))))
  (recurse 1))

(defn approximately? [v1 v2]
  (def tolerance 0.0001)
  (< (math/abs (- v1 v2)) tolerance))

(def golden-ratio (/ (+ 1 (math/sqrt 5)) 2))

(deftest exercise-37
         (is (approximately? (/ 1 golden-ratio) (cont-frac (fn [i] 1)
                                                           (fn [i] 1)
                                                           10))))
