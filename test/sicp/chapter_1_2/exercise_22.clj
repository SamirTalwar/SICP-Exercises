(ns sicp.chapter-1-2.exercise-22
  (:use clojure.test)
  (:require [sicp.chapter-1-2.exercise-21 :refer [smallest-divisor]]))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn timed-prime-test [n]
  (def is-prime? (prime n))
  (defn report-prime [elapsed-time]
    (.println System/out " *** ")
    (.println System/out elapsed-time))
  (defn start-prime-test [n start-time]
    (if is-prime?
      (report-prime (- (System/currentTimeMillis) start-time))))
  (newline)
  (.println System/out n)
  (start-prime-test n (System/currentTimeMillis))
  is-prime?)

(defn search-for-primes [min]
  (take 3 (filter prime? (iterate #(+ % 1) min))))

(deftest exercise-22
         (is (= '(1009 1013 1019) (search-for-primes 1001)))
         (is (= '(10007 10009 10037) (search-for-primes 10001)))
         (is (= '(100003 100019 100043) (search-for-primes 100001)))
         (is (= '(1000003 1000033 1000037) (search-for-primes 1000001))))

; This computer is too fast for a recognising single prime to take more than a few milliseconds.
; The task is therefore impossible to complete using the suggested approach.
