(ns sicp.chapter-1-3.exercise-38
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn cont-frac [n d k]
  (defn iter [denom k]
    (let [current-value (/ (n k) (denom k))]
      (if (= k 1)
        current-value
        (recur (fn [i] (+ (d i) current-value)) (- k 1)))))
  (iter d k))

(defn approximately? [v1 v2]
  (def tolerance 0.0001)
  (< (math/abs (- v1 v2)) tolerance))

(deftest exercise-38
         (is (approximately? (- Math/E 2) (cont-frac (fn [i] 1)
                                                     (fn [i] (if (= (rem i 3) 2)
                                                               (* (+ (/ (- i 2)
                                                                        3)
                                                                     1)
                                                                  2)
                                                               1))
                                                     7))))
