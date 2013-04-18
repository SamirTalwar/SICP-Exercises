(ns sicp.chapter-2-1.exercise-11
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]))

(defrecord Interval [lower upper])

(defn sign [value]
  (cond (< value 0) -1
        :else        1))

(defmethod arithmetic/* [Interval Interval]
  [x y]
  (let [ll (* (:lower x) (:lower y))
        lu (* (:lower x) (:upper y))
        ul (* (:upper x) (:lower y))
        uu (* (:upper x) (:upper y))]
    (case (map sign [(:lower x) (:upper x) (:lower y) (:upper y)])
      [ 1  1  1  1] (Interval/create {:lower ll, :upper uu})
      [ 1  1 -1  1] (Interval/create {:lower ul, :upper uu})
      [-1  1  1  1] (Interval/create {:lower lu, :upper uu})
      [-1  1 -1  1] (Interval/create {:lower (min lu ul), :upper uu})
      [ 1  1 -1 -1] (Interval/create {:lower ul, :upper lu})
      [-1 -1  1  1] (Interval/create {:lower lu, :upper ul})
      [-1  1 -1 -1] (Interval/create {:lower ul, :upper ll})
      [-1 -1 -1  1] (Interval/create {:lower lu, :upper ll})
      [-1 -1 -1 -1] (Interval/create {:lower uu, :upper ll}))))

(deftest exercise-11
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
         (is (= (Interval/create {:lower -32, :upper 12})
                (arithmetic/* (Interval/create {:lower -8, :upper 3}) (Interval/create {:lower -7, :upper 4}))))
         (is (= (Interval/create {:lower -63, :upper -8})
                (arithmetic/* (Interval/create {:lower 2, :upper 7}) (Interval/create {:lower -9, :upper -4}))))
         (is (= (Interval/create {:lower -72, :upper -12})
                (arithmetic/* (Interval/create {:lower -8, :upper -3}) (Interval/create {:lower 4, :upper 9}))))
         (is (= (Interval/create {:lower -30, :upper 72})
                (arithmetic/* (Interval/create {:lower -12, :upper 5}) (Interval/create {:lower -6, :upper -1}))))
         (is (= (Interval/create {:lower -5, :upper 55})
                (arithmetic/* (Interval/create {:lower -5, :upper -1}) (Interval/create {:lower -11, :upper 1}))))
         (is (= (Interval/create {:lower 32, :upper 144})
                (arithmetic/* (Interval/create {:lower -16, :upper -8}) (Interval/create {:lower -9, :upper -4})))))
