(ns sicp.chapter-2-1.exercise-08
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]))

(defrecord Interval [lower upper])

(defmethod arithmetic/- [Interval Interval]
  [x y]
  (Interval/create {:lower (arithmetic/- (:lower x) (:lower y))
                    :upper (arithmetic/- (:upper x) (:upper y))}))

(deftest exercise-08
         (is (= (Interval/create {:lower 1, :upper 2})
                (arithmetic/- (Interval/create {:lower 3, :upper 7}) (Interval/create {:lower 2, :upper 5})))))
