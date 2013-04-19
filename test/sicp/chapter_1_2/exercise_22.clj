(ns sicp.chapter-1-2.exercise-22
  (:use clojure.test)
  (:require [sicp.chapter-1-2.exercise-21 :refer [smallest-divisor]]
            [clojure.string :as string]))

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

(deftest exercise-22
         (is (= [1009 1013 1019] (search-for-primes 1001 3)))
         (is (= [10007 10009 10037] (search-for-primes 10001 3)))
         (is (= [100003 100019 100043] (search-for-primes 100001 3)))
         (is (= [1000003 1000033 1000037] (search-for-primes 1000001 3))))

; The growth in time seems to be consistent with the algorithm's growth of ϴ(√n).
;
; Lines have been truncated for brevity.

; Output:
;
; user=> (timed-prime-test 1001)
; Start value: 1001
; First 100 primes: 1009, 1013, 1019, 1021, 1031, ...
; Time taken (ms): 2
;
; user=> (timed-prime-test 10001)
; Start value: 10001
; First 100 primes: 10007, 10009, 10037, 10039, ...
; Time taken (ms): 5
;
; user=> (timed-prime-test 100001)
; Start value: 100001
; First 100 primes: 100003, 100019, 100043, 100049, 100057, ...
; Time taken (ms): 7
;
; user=> (timed-prime-test 1000001)
; Start value: 1000001
; First 100 primes: 1000003, 1000033, 1000037, 1000039, 1000081, ...
; Time taken (ms): 12
;
; user=> (timed-prime-test 10000001)
; Start value: 10000001
; First 100 primes: 10000019, 10000079, 10000103, 10000121, 10000139, ...
; Time taken (ms): 35
