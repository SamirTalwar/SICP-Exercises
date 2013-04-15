(ns sicp.chapter-1-2.exercise-27
  (:use clojure.test)
  (:require [clojure.string :as string]))

(defn square [n] (* n n))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m))
                         m)
        :else (rem (* base
                      (expmod base (- exp 1) m))
                   m)))

(defn fermat-test [n test-value]
  (= (expmod test-value n n) test-value))

(defn carmichael [n]
  (every? #(fermat-test n %) (range 1 n)))

(deftest exercise-27
         (is (carmichael 561))
         (is (carmichael 1105))
         (is (carmichael 1729))
         (is (carmichael 2465))
         (is (carmichael 2821))
         (is (carmichael 6601)))
