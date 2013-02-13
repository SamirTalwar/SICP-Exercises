(ns sicp.chapter-1-3.exercise-39
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn cont-frac [n d k]
  (defn iter [denom k]
    (let [current-value (/ (n k) (denom k))]
      (if (= k 1)
        current-value
        (recur (fn [i] (+ (d i) current-value)) (- k 1)))))
  (iter d k))

(defn tan-cf [x k]
  (- (cont-frac (fn [i] (- (if (= i 1) x (* x x))))
                (fn [i] (- (* i 2) 1))
                k)))

(defn approximately? [v1 v2]
  (def tolerance 0.0001)
  (< (math/abs (- v1 v2)) tolerance))

(deftest exercise-39
         (is (approximately? 1 (tan-cf (/ Math/PI 4) 10)))
         (is (approximately? 0 (tan-cf Math/PI 10))))
