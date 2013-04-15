(ns sicp.chapter-1-3.exercise-39
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

(defn tan-cf [x k]
  (- (cont-frac (fn [i] (- (if (= i 1) x (* x x))))
                (fn [i] (- (* i 2) 1))
                k)))

(deftest exercise-39
         (is (approximately? 1 (tan-cf (/ Math/PI 4) 10)))
         (is (approximately? 0 (tan-cf Math/PI 10))))
