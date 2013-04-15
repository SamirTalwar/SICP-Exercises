(ns sicp.chapter-1-3.exercise-29
  (:use clojure.test)
  (:require [sicp.comparisons :refer [approximately?]]))

(defn cube [n] (* n n n))

(defn integral [f a b n]
  (def h (/ (- b a) n))
  (defn y [k] (f (+ a (* k h))))
  (def coefficients
    (flatten (list [1] (take (- n 1) (flatten (repeat [4 2]))) [1])))

  (* (/ h 3)
     (->> (range (+ n 1))
          (map y)
          (map list coefficients)
          (map (fn [part] (let [[coefficient value] part] (* coefficient value))))
          (apply +))))

(deftest exercise-29
         (is (approximately? 0.25 (integral cube 0 1 100)))
         (is (approximately? 0.25 (integral cube 0 1 1000))))
