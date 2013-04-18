(ns sicp.chapter-2-1.exercise-07
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]))

(defrecord Interval [lower upper])

(defmethod arithmetic/+ [Interval Interval]
  [x y]
  (Interval/create {:lower (arithmetic/+ (:lower x) (:lower y))
                    :upper (arithmetic/+ (:upper x) (:upper y))}))

(deftest exercise-07
         (is (= (Interval/create {:lower 5, :upper 12})
                (arithmetic/+ (Interval/create {:lower 3, :upper 7}) (Interval/create {:lower 2, :upper 5})))))
