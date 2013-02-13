(ns sicp.chapter-1-3.exercise-37
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn cont-frac-recursive [n d k]
  (defn recurse [i]
    (if (> i k)
      0
      (/ (n i) (+ (d i) (recurse (+ i 1))))))
  (recurse 1))

(defn cont-frac-iterative [n d k]
  (defn iter [denom k]
    (let [current-value (/ (n k) (denom k))]
      (if (= k 1)
        current-value
        (recur (fn [i] (+ (d i) current-value)) (- k 1)))))
  (iter d k))

(defn approximately? [v1 v2]
  (def tolerance 0.0001)
  (< (math/abs (- v1 v2)) tolerance))

(def golden-ratio (/ (+ 1 (math/sqrt 5)) 2))

(deftest exercise-37
         (is (approximately? (/ 1 golden-ratio) (cont-frac-recursive (fn [i] 1)
                                                                     (fn [i] 1)
                                                                     10)))
         (is (approximately? (/ 1 golden-ratio) (cont-frac-iterative (fn [i] 1)
                                                                     (fn [i] 1)
                                                                     10))))
