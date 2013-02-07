(ns sicp.chapter-1-2.exercise-24
  (:use clojure.test)
  (:require [clojure.string :as string]))

(defn square [n] (* n n))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(defn prime? [n]
  (fast-prime? n 5))

(defn search-for-primes [min count]
  (take count (filter prime? (iterate #(+ % 1) min))))

(defn timed-prime-test [n]
  (print "Start value: ")
  (println n)
  (let [start (System/currentTimeMillis)]
    (print "First 1000 primes: ")
    (println (string/join ", " (search-for-primes n 1000)))
    (let [end (System/currentTimeMillis)]
      (print "Time taken (ms): ")
      (println (- end start)))))

(deftest exercise-24
         (is (= '(1009 1013 1019) (search-for-primes 1001 3)))
         (is (= '(10007 10009 10037) (search-for-primes 10001 3)))
         (is (= '(100003 100019 100043) (search-for-primes 100001 3)))
         (is (= '(1000003 1000033 1000037) (search-for-primes 1000001 3))))

; The increase does seem to be logarithmic-ish.

; Output:
;
; user=> (timed-prime-test 1001)
; Start value: 1001
; First 1000 primes: 1009, 1013, 1019, 1021, 1031, ...
; Time taken (ms): 45
;
; user=> (timed-prime-test 10001)
; Start value: 10001
; First 1000 primes: 10007, 10009, 10037, 10039, 10061, ...
; Time taken (ms): 57
;
; user=> (timed-prime-test 100001)
; Start value: 100001
; First 1000 primes: 100003, 100019, 100043, 100049, 100057, ...
; Time taken (ms): 64
;
; user=> (timed-prime-test 1000001)
; Start value: 1000001
; First 1000 primes: 1000003, 1000033, 1000037, 1000039, 1000081, ...
; Time taken (ms): 82
