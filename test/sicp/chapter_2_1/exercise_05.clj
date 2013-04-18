(ns sicp.chapter-2-1.exercise-05
  (:use clojure.test)
  (:require [clojure.math.numeric-tower :refer [expt]]))

(defn cons [a b]
  (* (expt 2 a) (expt 3 b)))

(defn- pair-breaker [divisor]
  (fn [pair]
    ((fn [pair value]
       (if (= (rem pair divisor) 0)
         (recur (/ pair divisor) (inc value))
         value))
     pair 0)))

(def car (pair-breaker 2))

(def cdr (pair-breaker 3))

(deftest exercise-05
         (is (= 0 (car (cons 0 0))))
         (is (= 0 (cdr (cons 0 0))))

         (is (= 3 (car (cons 3 4))))
         (is (= 4 (cdr (cons 3 4))))

         (is (= 9 (car (cons 9 5))))
         (is (= 5 (cdr (cons 9 5)))))
