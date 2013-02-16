(ns sicp.chapter-1-3.exercise-44
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defn smooth [f dx]
  (fn [x] (/ (+ (f x)
                (f (- x dx))
                (f (+ x dx)))
             3)))

(defn repeated [f n]
  ((fn [func times]
     (if (= times 1)
       func
       (recur (comp f func) (dec times))))
     f n))

(defn smooth-n [f n dx]
  ((repeated (fn [func] (smooth func dx)) n) f))

(defn square-wave [x]
  (let [offset (mod (int (math/floor x)) 4)]
    ({0 0, 1 1, 2 0, 3 -1} offset)))

(def smoothened-square-wave
  (smooth square-wave 0.5))

(def flattened-square-wave
  (smooth-n square-wave 5 0.5))

(defn approximately? [v1 v2]
  (def tolerance 0.00001)
  (< (math/abs (- v1 v2)) tolerance))

(deftest exercise-44
         (is (= 1 (square-wave 1.5)))
         (is (approximately? 2/3 (smoothened-square-wave 1.5)))
         (is (= 0 (square-wave 2.25)))
         (is (approximately? 1/3 (smoothened-square-wave 2.25)))
         (is (= -1 (square-wave 3.75)))
         (is (approximately? -2/3 (smoothened-square-wave 3.75)))
         (is (= 1 (square-wave 1)))
         (is (> 0.3 (flattened-square-wave 1))))
