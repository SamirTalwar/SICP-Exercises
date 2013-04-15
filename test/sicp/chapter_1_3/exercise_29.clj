(ns sicp.chapter-1-3.exercise-29
  (:use clojure.test)
  (:require [sicp.comparisons :refer [approximately?]]))

(defn cube [n] (* n n n))

(defn integral [f a b n]
  (def h (/ (- b a) n))
  (defn y [k] (f (+ a (* k h))))
  (def coefficients
    (concat [1] (take (dec n) (apply concat (repeat [4 2]))) [1]))

  (* (/ h 3)
     (->> (range (inc n))
          (map y)
          (map list coefficients)
          (map (fn [[coefficient value]] (* coefficient value)))
          (apply +))))

(deftest exercise-29
         (is (approximately? 0.25 (integral cube 0 1 100)))
         (is (approximately? 0.25 (integral cube 0 1 1000))))
