(ns sicp.chapter-1-3.exercise-38
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]
            [sicp.comparisons :refer [approximately?]]))

(defn cont-frac [n d k]
  (defn iter [denom k]
    (let [current-value (/ (n k) (denom k))]
      (if (= k 1)
        current-value
        (recur (fn [i] (+ (d i) current-value)) (- k 1)))))
  (iter d k))

(deftest exercise-38
         (is (approximately? (- Math/E 2) (cont-frac (fn [i] 1)
                                                     (fn [i] (if (= (rem i 3) 2)
                                                               (* (+ (/ (- i 2)
                                                                        3)
                                                                     1)
                                                                  2)
                                                               1))
                                                     7))))
