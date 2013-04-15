(ns sicp.chapter-1-2.exercise-19
  (:use clojure.test))

; Tpq(Tpq(a, b)) = Tp'q'(a, b)
;                = ( q(bp + aq) + (q + p)(bq + aq + ap), p(bp + aq) + q(bq + aq + ap) )
;                = ( bpq + aq² + bq² + aq² + apq + bpq + apq + ap², bp² + apq + bq² + aq² + apq )
;                = ( ap² + 2aq² + bq² + 2apq + 2bpq, aq² + bp² + bq² + 2apq )
;                = ( b(q² + 2pq) + a(q² + 2pq) + a(p² + q²), b(p² + q²) + a(q² + 2pq) )
;
; (p', q') = (p² + q², q² + 2pq)

(defn fib [n]
  (defn square [x] (* x x))
  (defn fib-iter [a b p q count]
    (cond (= count 0) b
          (even? count) (fib-iter a
                                  b
                                  (+ (square p) (square q))
                                  (+ (square q) (* 2 p q))
                                  (/ count 2))
          :else (fib-iter (+ (* b q) (* a q) (* a p))
                          (+ (* b p) (* a q))
                          p
                          q
                          (dec count))))
  (fib-iter 1 0 0 1 n))

(deftest exercise-19
         (is (= 1 (fib 1)))
         (is (= 1 (fib 2)))
         (is (= 2 (fib 3)))
         (is (= 3 (fib 4)))
         (is (= 5 (fib 5)))
         (is (= 8 (fib 6)))
         (is (= 13 (fib 7)))
         (is (= 21 (fib 8)))
         (is (= 34 (fib 9)))
         (is (= 55 (fib 10))))
