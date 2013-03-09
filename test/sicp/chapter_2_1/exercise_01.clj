(ns sicp.chapter-2-1.exercise-01
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(deftype Pair [a b])

(defn make-rat [n d]
  (let [g (math/gcd n d)
        sign (/ d (math/abs d))]
    (Pair.
      (/ n g sign)
      (/ d g sign))))

(defn numer [rat] (.a rat))
(defn denom [rat] (.b rat))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))
(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))
(defn print-rat [x]
  (str (.toString (numer x)) "/" (.toString (denom x))))

(deftest exercise-01
         (is (= "2/3" (print-rat (make-rat 4 6))))
         (is (= "4/5" (print-rat (add-rat (make-rat 3 6) (make-rat 3 10)))))
         (is (= "8/9" (print-rat (mul-rat (make-rat 2 3) (make-rat 4 3)))))
         (is (= "-2/5" (print-rat (make-rat -6 15))))
         (is (= "-5/3" (print-rat (make-rat 5 -3))))
         (is (= "7/10" (print-rat (make-rat -7 -10)))))
