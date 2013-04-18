(ns sicp.chapter-2-1.exercise-10
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic :refer [defmethod* qsym]]))

(defrecord Interval [lower upper])

(defmethod arithmetic/* [Interval Interval]
  [x y]
  (let [p1 (* (:lower x) (:lower y))
        p2 (* (:lower x) (:upper y))
        p3 (* (:upper x) (:lower y))
        p4 (* (:upper x) (:upper y))]
    (Interval/create {:lower (min p1 p2 p3 p4)
                      :upper (max p1 p2 p3 p4)})))

(defmethod* arithmetic / [Interval Interval]
  [x y]
  (when (or (< (:lower x) 0 (:upper x))
            (< (:lower y) 0 (:upper y)))
    (throw (ArithmeticException. "Interval spans zero")))
  (arithmetic/*
    x
    (Interval/create {:lower (/ (:upper y))
                      :upper (/ (:lower y))})))

(deftest exercise-10
         (is (= (Interval/create {:lower 1/5, :upper 5/4})
                ((qsym arithmetic /) (Interval/create {:lower 2, :upper 5})
                                     (Interval/create {:lower 4, :upper 10}))))
         (is (thrown? ArithmeticException
                ((qsym arithmetic /) (Interval/create {:lower 3, :upper 5})
                                     (Interval/create {:lower -4, :upper 4}))))
         (is (thrown? ArithmeticException
                ((qsym arithmetic /) (Interval/create {:lower -3, :upper 5})
                                     (Interval/create {:lower 4, :upper 4})))) )
