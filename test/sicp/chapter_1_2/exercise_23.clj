(ns sicp.chapter-1-2.exercise-23
  (:use clojure.test)
  (:require [clojure.string :as string]))

(defn smallest-divisor [n]
  (letfn [(next-possible [number]
            (if (= number 2)
              3
              (+ number 2)))
          (divides? [a b]
            (= (rem b a) 0))
          (find-divisor [n test-divisor]
            (cond (> (* test-divisor test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (find-divisor n (next-possible test-divisor))))]
    (find-divisor n 2)))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn search-for-primes [min count]
  (take count (filter prime? (iterate inc min))))

(defn timed-prime-test [n]
  (print "Start value: ")
  (println n)
  (let [start (System/currentTimeMillis)]
    (print "First 100 primes: ")
    (println (string/join ", " (search-for-primes n 100)))
    (let [end (System/currentTimeMillis)]
      (print "Time taken (ms): ")
      (println (- end start)))))

(deftest exercise-23
         (is (= [1009 1013 1019] (search-for-primes 1001 3)))
         (is (= [10007 10009 10037] (search-for-primes 10001 3)))
         (is (= [100003 100019 100043] (search-for-primes 100001 3)))
         (is (= [1000003 1000033 1000037] (search-for-primes 1000001 3))))

; The algorithms run much faster. Not twice as fast, but I think this is due to the inaccuracy of the measurement.

; Output:
;
; user=> (timed-prime-test 1001)
; Start value: 1001
; First 100 primes: 1009, 1013, 1019, 1021, 1031, ...
; Time taken (ms): 4
;
; user=> (timed-prime-test 10001)
; Start value: 10001
; First 100 primes: 10007, 10009, 10037, 10039, 10061, ...
; Time taken (ms): 4
;
; user=> (timed-prime-test 100001)
; Start value: 100001
; First 100 primes: 100003, 100019, 100043, 100049, 100057, ...
; Time taken (ms): 6
;
; user=> (timed-prime-test 1000001)
; Start value: 1000001
; First 100 primes: 1000003, 1000033, 1000037, 1000039, 1000081, ...
; Time taken (ms): 11
