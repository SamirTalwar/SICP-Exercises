(ns sicp.chapter-2-1.exercise-11
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]))

(defrecord Interval [lower upper])

(defmethod arithmetic/* [Interval Interval]
  [x y]
  (let [p1 (* (:lower x) (:lower y))
        p2 (* (:lower x) (:upper y))
        p3 (* (:upper x) (:lower y))
        p4 (* (:upper x) (:upper y))]
    (Interval/create {:lower (min p1 p2 p3 p4)
                      :upper (max p1 p2 p3 p4)})))

(deftest exercise-10
         (is (= (Interval/create {:lower 0, :upper 0})
                (arithmetic/* (Interval/create {:lower 0, :upper 0}) (Interval/create {:lower 4, :upper 10}))))
         (is (= (Interval/create {:lower 0, :upper 0})
                (arithmetic/* (Interval/create {:lower 3, :upper 5}) (Interval/create {:lower 0, :upper 0}))))
         (is (= (Interval/create {:lower 12, :upper 50})
                (arithmetic/* (Interval/create {:lower 3, :upper 5}) (Interval/create {:lower 4, :upper 10}))))
         (is (= (Interval/create {:lower -20, :upper 70})
                (arithmetic/* (Interval/create {:lower -2, :upper 7}) (Interval/create {:lower 4, :upper 10}))))
         (is (= (Interval/create {:lower -45, :upper 30})
                (arithmetic/* (Interval/create {:lower 3, :upper 5}) (Interval/create {:lower -9, :upper 6}))))
         (is (= (Interval/create {:lower -63, :upper 42})
                (arithmetic/* (Interval/create {:lower -2, :upper 7}) (Interval/create {:lower -9, :upper 6}))))
         (is (= (Interval/create {:lower -63, :upper -8})
                (arithmetic/* (Interval/create {:lower 2, :upper 7}) (Interval/create {:lower -9, :upper -4})))))
